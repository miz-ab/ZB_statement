package com.zbs.zb.controller;


import com.zbs.zb.dao.*;
import com.zbs.zb.db_model.OracleStatement;
import com.zbs.zb.db_model.OracleStatementDetail;
import com.zbs.zb.db_model.StatementDetail;
import com.zbs.zb.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;


@Slf4j
//@CrossOrigin(origins = {"http://localhost:8082", "http://127.0.0.1:3012"})
@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private StatementService statementService;

    @Autowired
    private ServiceCaller serviceCaller;

    @Autowired
    DssService dssService;

    @Autowired
    EmailService emailService;

    @Autowired
    ExtractStatementService extractStatementService;
    /*
    *
    * */
    @PostMapping("/post-statement")
    //@Scheduled(fixedRate = 60000)
    //change StatementRequest -> Statement
    public ResponseEntity<Map<String, Object>> SavePostDataService(@RequestBody Statement statement_response){
        try{
            Map<String, Object> response = new HashMap<>();
            //Statement statement_response = serviceCaller.bankStatement(s);

            log.info("response {} ", statement_response);

            /* insert to mysql database */
            log.info("api date {} ", statement_response.getSTATEMENT_PERIOD());
            int status = get_date_comparison_value(statement_response.getSTATEMENT_PERIOD());
            log.info("date status {}", status);

            /*
            * 2 add both line and header
            * 1 add only line
            * */

            /*
            * change, add statement header interface regardless of the day difference,
            * add statement data for each request
            * */

            switch (status) {
                case 1, 2 -> {
                    /* add statement line and statement interface (new change) */
                    OracleStatement oracleStatement_ = extractStatementService.get_oracle_statement(statement_response);
                    log.info("statement int case 1{} ", oracleStatement_);
                    statementService.insertStatementOracleDB(oracleStatement_);

                    List<OracleStatementDetail> oracleStatementDetailList = extractStatementService.get_oracle_statement_detail(statement_response);
                    log.info(" oracle statement detail list {} ", oracleStatementDetailList);
                    //List<StatementDetail> statementDetailList = extractStatementService.get_statement_detail(s);

                    /* insert to oracle database */
                    for (OracleStatementDetail oracleStatementDetail : oracleStatementDetailList) {
                        String a = statementService.insertStatementDetailOracleDB(oracleStatementDetail);
                        log.info("oracle statement detail list DB response {} ", a);
                    }

                    response.put("statement_detail", oracleStatementDetailList);
                    response.put("statement created successfully", oracleStatementDetailList.size());
                }
                default -> response.put("statement_detail", "Invalid Data");
            }
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    @PostMapping("/get-cli-summary-report")
    public ResponseEntity<Map<String, Object>> SavePostDataService(@RequestBody summaryRequest summaryRequest){
            try{
                Map<String, Object> response = new HashMap<>();
                SummaryReport res = dssService.getSummaryReport(summaryRequest);
                log.info("summary response {}", res);
                response.put("res ", res);
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }catch (Exception e){
                log.error(e.getMessage());
                return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
    }
    */
    @PostMapping("/get-balance")
    public AccountBalanceResponse getBalance(@RequestBody AccountBalance b){
        try{
            return  serviceCaller.bankStatementBalance(b);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @PostMapping("/get-statement-list")
    public Statement getBankStatement(@RequestBody StatementRequest t){
        try{
            return  serviceCaller.bankStatement(t);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }

    @PostMapping("/send-mail")
    public void sendMail(@RequestBody Mail mail){
        log.info("mail " + mail);
        emailService.sendEmail(mail);
    }


   private int get_date_comparison_value(String api_date){
        String db_date_val = statementService.getStatementHeaderIdAndLatestDate();
        String db_date = db_date_val.split("@")[1];

        log.info("date_c {}", db_date);
        log.info("date_a {}", api_date);

        int r = statementService.compareDate_(db_date, api_date.split(" - ")[0]);
        return statementService.compareDate_(db_date, api_date.split(" - ")[0]);
   }

}
