package com.zbs.zb.dao;

import com.zbs.zb.db_model.OracleStatement;
import com.zbs.zb.db_model.OracleStatementDetail;
import com.zbs.zb.db_model.StatementDetail;
import com.zbs.zb.model.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.zbs.zb.constants.Constants.CHAR_NUMERIC;


@Service
public class ExtractStatementService {

    @Autowired
    private StatementService statementService;

    public com.zbs.zb.db_model.Statement get_statement(Statement s){
        UUID statement_uuid = UUID.randomUUID();
        return new
                com.zbs.zb.db_model.Statement(statement_uuid.toString(),
                s.getSTATUS(),
                s.getSTATUS_CODE(),
                s.getMESSAGE(),
                s.getCUSTOMER_NAME(),
                s.getACCOUNT_TYPE(),
                s.getACCOUNT_CCY(),
                s.getACCOUNT_NUMBER(),
                s.getSTATEMENT_PERIOD(),
                s.getOPENING_BALANCE(),
                s.getCLOSING_BALANCE());
    }

    public List<StatementDetail> get_statement_detail(Statement s){
        List<StatementDetail> StatementDetailList = new ArrayList<>();
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(10000));

        IntStream.range(0, s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().size()).forEachOrdered(i -> {
            UUID statementdetail_uuid = UUID.randomUUID();
            StatementDetail statementDetail = new StatementDetail(
                    id,
                    statementdetail_uuid.toString(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCUSTOMER_NAME(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_NUMBER(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getBRANCH(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_REF_NO(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getOPENING_BALANCE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCLOSING_BALANCE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getVALUE_DATE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getDEBIT_AMOUNT(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_CCY(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getINSTRUMENT_NO(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DESC(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getNARRATIVE()
            );
            StatementDetailList.add(statementDetail);
        });

        return StatementDetailList;
    }

   public List<OracleStatementDetail> get_oracle_statement_detail(Statement s){

       String db_date_and_id_val = statementService.getStatementHeaderIdAndLatestDate();
       String db_id = db_date_and_id_val.split("@")[0];

       int new_line_no = Integer.parseInt(statementService.get_statement_interface_line_number());

       List<OracleStatementDetail> oracleStatementDetailList = new ArrayList<>();

       for(int i = 0; i < s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().size(); i++){
           new_line_no = new_line_no + 1;
           //int new_line_no = Integer.parseInt(statementService.get_statement_interface_line_number()) + 1;
           OracleStatementDetail oracleStatementDetail = new OracleStatementDetail(
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_NUMBER(),
                   db_id,
                   String.valueOf(new_line_no),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getNARRATIVE(),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DESC(),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT(),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_CCY(),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_REF_NO(),
                   generate_random_id(),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE()
           );
           oracleStatementDetailList.add(oracleStatementDetail);
       }

       return oracleStatementDetailList;
   }

   public OracleStatement get_oracle_statement(Statement s){
       String db_date_and_id_val = statementService.getStatementHeaderIdAndLatestDate();
       String db_id = db_date_and_id_val.split("@")[0];
       String new_id = String.valueOf(Integer.parseInt(db_id) + 1);

       return new OracleStatement(
               new_id,
               s.getACCOUNT_NUMBER(),
               s.getSTATEMENT_PERIOD(),
               "Zemen Bank",
               "Abinet Branch",
               s.getOPENING_BALANCE(),
               s.getCLOSING_BALANCE(),
               s.getSTATUS().substring(0,1),
               s.getACCOUNT_CCY()
       );
   }

    public String generate_trx_code(){
        int length = 6;
           String CHARACTERS = CHAR_NUMERIC;
           SecureRandom RANDOM = new SecureRandom();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                int randomIndex = RANDOM.nextInt(CHARACTERS.length());
                sb.append(CHARACTERS.charAt(randomIndex));
            }
            return sb.toString();
    }

    public Integer generate_random_id(){
        Random random = new Random();
        return   1000 + random.nextInt(9000);
    }
}
