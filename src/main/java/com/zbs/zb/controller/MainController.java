package com.zbs.zb.controller;

import com.zbs.zb.dao.ExtractStatementService;
import com.zbs.zb.dao.ServiceCaller;
import com.zbs.zb.dao.StatementService;
import com.zbs.zb.db_model.OracleStatement;
import com.zbs.zb.db_model.OracleStatementDetail;
import com.zbs.zb.db_model.StatementDetail;
import com.zbs.zb.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;


@CrossOrigin(origins = {"http://localhost:8082", "http://127.0.0.1:3012"})
@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private StatementService statementService;

    @Autowired
    private ServiceCaller serviceCaller;

    @Autowired
    ExtractStatementService extractStatementService;
    /*
    *
    * */
    @PostMapping("/post-statement")
    //@Scheduled(fixedRate = 60000)
    public ResponseEntity<Map<String, Object>> SavePostDataService(@RequestBody Statement s){

        try{
            Map<String, Object> response = new HashMap<>();
            //Statement s = serviceCaller.getStatement();

            int status = get_date_comparison_value(s.getSTATEMENT_PERIOD());
            System.out.println("status code " + status);
            System.out.println("statement period " + s.getSTATEMENT_PERIOD());
            if(status == 1){
                /* add statement line only */
                List<OracleStatementDetail> oracleStatementDetailList = extractStatementService.get_oracle_statement_detail(s);
                List<StatementDetail> statementDetailList = extractStatementService.get_statement_detail(s);

                /* insert to oracle database */
                for(OracleStatementDetail oracleStatementDetail : oracleStatementDetailList) {
                    statementService.insertStatementDetailOracleDB(oracleStatementDetail);
                }
                /* insert to  mysql database */
                for(StatementDetail statementDetail : statementDetailList) {
                    statementService.insertStatementDetailData(statementDetail);
                }
                response.put("statement_detail", oracleStatementDetailList);
                response.put("statement created successfully", oracleStatementDetailList.size());

            }else if(status == 2){
                /* add both statement header and line and statement and statement detail list */
                OracleStatement oracleStatement_ = extractStatementService.get_oracle_statement(s);
                statementService.insertStatementOracleDB(oracleStatement_);
                List<OracleStatementDetail> oracleStatementDetailList = extractStatementService.get_oracle_statement_detail(s);

                for(OracleStatementDetail oracleStatementDetail : oracleStatementDetailList){
                    statementService.insertStatementDetailOracleDB(oracleStatementDetail);
                }
                /* insert to mysql database */

                com.zbs.zb.db_model.Statement statement = extractStatementService.get_statement(s);
                List<StatementDetail> statementDetailList = extractStatementService.get_statement_detail(s);
                statementService.insertStatementData(statement);

                for(StatementDetail statementDetail : statementDetailList) {
                    statementService.insertStatementDetailData(statementDetail);
                }

                response.put("statement_header", oracleStatement_);
                response.put("statement_detail", oracleStatementDetailList);
                response.put("statement created successfully", oracleStatementDetailList.size());

            }else{
                response.put("statement_detail", "Invalid Data");
            }
            return new ResponseEntity<>(response, HttpStatus.CREATED);


        } catch (Exception e) {
            System.out.println("err " + e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

   private int get_date_comparison_value(String api_date){
        String db_date_val = statementService.getStatementHeaderIdAndLatestDate();
        String db_date = db_date_val.split("@")[1];
        int r = statementService.compareDate_(db_date, api_date);
        return statementService.compareDate_(db_date, api_date);
   }

}
