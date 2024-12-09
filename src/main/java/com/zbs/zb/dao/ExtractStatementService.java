package com.zbs.zb.dao;

import com.zbs.zb.db_model.OracleStatement;
import com.zbs.zb.db_model.OracleStatementDetail;
//import com.zbs.zb.db_model.StatementDetail;
import com.zbs.zb.db_model.StatementDetail;
import com.zbs.zb.model.Statement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.zbs.zb.constants.Constants.*;


@Slf4j
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


//    public List<com.zbs.zb.db_model.StatementDetail> get_statement_detail(Statement s){
//        List<com.zbs.zb.db_model.StatementDetail> StatementDetailList = new ArrayList<>();
//        Random random = new Random();
//        String id = String.format("%04d", random.nextInt(10000));
//
//        IntStream.range(0, s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().size()).forEachOrdered(i -> {
//            UUID statementdetail_uuid = UUID.randomUUID();
//            com.zbs.zb.db_model.StatementDetail statementDetail = new com.zbs.zb.db_model.StatementDetail(
//                    id,
//                    statementdetail_uuid.toString(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCUSTOMER_NAME(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_NUMBER(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getBRANCH(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_REF_NO(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getOPENING_BALANCE(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCLOSING_BALANCE(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getVALUE_DATE(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getDEBIT_AMOUNT(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_CCY(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getINSTRUMENT_NO(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DESC(),
//                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getNARRATIVE()
//            );
//            StatementDetailList.add(statementDetail);
//        });
//
//        return StatementDetailList;
//    }

    public List<com.zbs.zb.db_model.StatementDetail> get_statement_detail_for_mysql(Statement s){
        List<com.zbs.zb.db_model.StatementDetail> StatementDetailList = new ArrayList<>();
        Random random = new Random();
        String id = String.format("%04d", random.nextInt(10000));

        IntStream.range(0, s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().size()).forEachOrdered(i -> {
            UUID statementdetail_uuid = UUID.randomUUID();

            com.zbs.zb.db_model.StatementDetail statementDetail = new com.zbs.zb.db_model.StatementDetail(
                    id,
                    statementdetail_uuid.toString(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCUSTOMER_NAME(),
                    ABINET_BRANCH_ACCOUNT_NO,
                    ABINET_BRANCH_NAME,
                    //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getBRANCH(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_REF_NO(),
                    //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getVALUE_DATE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getOPENING_BALANCE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCLOSING_BALANCE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getVALUE_DATE(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getDEBIT_AMOUNT(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_CCY(),
                    DEFAULT_INST_NO,
                    //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getINSTRUMENT_NO(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DESC(),
                    s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getNARRATIVE()
            );
            StatementDetailList.add(statementDetail);
        });

        return StatementDetailList;
    }



//   public List<OracleStatementDetail> get_oracle_statement_detail(Statement s){
//
//       String db_date_and_id_val = statementService.getStatementHeaderIdAndLatestDate();
//       String db_id = db_date_and_id_val.split("@")[0];
//
//       int new_line_no = Integer.parseInt(statementService.get_statement_interface_line_number());
//
//       List<OracleStatementDetail> oracleStatementDetailList = new ArrayList<>();
//
//       for(int i = 0; i < s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().size(); i++){
//           new_line_no = new_line_no + 1;
//           //int new_line_no = Integer.parseInt(statementService.get_statement_interface_line_number()) + 1;
//
//           OracleStatementDetail oracleStatementDetail = new OracleStatementDetail(
//                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_NUMBER(),
//                   db_id,
//                   String.valueOf(new_line_no),
//                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
//                   creditOrDebit(s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT()),
//                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getNARRATIVE(),
//                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
//                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DESC(),
//                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT(),
//                   getCreditOrDebitAmount(s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT(), s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getDEBIT_AMOUNT()),
//                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_CCY(),
//                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_REF_NO(),
//                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getNARRATIVE(),
//                   generate_random_id(),
//                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
//                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_REF_NO(),
//                   ETB_CCY
//           );
//           oracleStatementDetailList.add(oracleStatementDetail);
//       }
//
//       return oracleStatementDetailList;
//   }

    public List<OracleStatementDetail> get_oracle_statement_detail(Statement s){

       String db_date_and_id_val = statementService.getStatementHeaderIdAndLatestDate();

       log.info("db date and id {} ", db_date_and_id_val);
       String db_id = db_date_and_id_val.split("@")[0];
       String db_date = statementService.getStatementLineInterfaceIdAndLatestDate().equals("-1") ?
               "-1" : statementService.getStatementLineInterfaceIdAndLatestDate().split("@")[1];
        //String db_date = db_date_and_id_val.split("@")[1];
       String api_date = s.getSTATEMENT_PERIOD().split(" - ")[0];


       log.info("db date {}", db_date);
       log.info("api date {}", api_date);
        log.info("db id {}", db_id);
       int get_date_comparison = db_date.equals("-1")? DAY_MONTH_DIFFERENCE :  statementService.compareDate_(db_date, api_date);

       int new_line_no = get_date_comparison == DAY_MONTH_DIFFERENCE ? 0 : Integer.parseInt(statementService.get_statement_interface_line_number());
       log.info("line no {} ", new_line_no);

       List<OracleStatementDetail> oracleStatementDetailList = new ArrayList<>();

       /*
       * if statement detail is null return empty array
       * */

        if(s.getSTATEMENT_DETAILS() == null){
            return oracleStatementDetailList;
        }
        double sum_debit_amt = 0.0;
        double sum_credit_amt = 0.0;

       for(int i = 0; i < s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().size(); i++){
           new_line_no = new_line_no + 1;

           sum_debit_amt = sum_debit_amt + s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getDEBIT_AMOUNT();
           sum_credit_amt = sum_credit_amt + s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT();


           //int new_line_no = Integer.parseInt(statementService.get_statement_interface_line_number()) + 1;

           OracleStatementDetail oracleStatementDetail = new OracleStatementDetail(
                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_NUMBER(),
                   ABINET_BRANCH_ACCOUNT_NO,
                   db_id,
                   String.valueOf(new_line_no),
                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
                   format_date(s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getVALUE_DATE()),
                   creditOrDebit(s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT()),
                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getNARRATIVE(),
                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DATE(),
                   format_date(s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getVALUE_DATE()),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_DESC(),
                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT(),
                   getCreditOrDebitAmount(s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getCREDIT_AMOUNT(), s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getDEBIT_AMOUNT()),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getACCOUNT_CCY(),
                   //s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_REF_NO(),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getNARRATIVE(),
                   generate_random_id(),
                   format_date(s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getVALUE_DATE()),
                   s.getSTATEMENT_DETAILS().getSTATEMENT_DETAIL().get(i).getTRANSACTION_REF_NO(),
                   ETB_CCY
           );
           oracleStatementDetailList.add(oracleStatementDetail);
       }


        log.info("debit amt {}", sum_debit_amt);
        log.info("credit amt {}", sum_credit_amt);
        log.info("opening balance {}", s.getOPENING_BALANCE());
        log.info("closing balance {}", s.getCLOSING_BALANCE());

        double sum = (s.getOPENING_BALANCE() + sum_credit_amt) - sum_debit_amt;

        log.info("closing amt  and sum value {},{}", s.getCLOSING_BALANCE(),sum);

       return oracleStatementDetailList;
   }

//   public OracleStatement get_oracle_statement(Statement s){
//       String db_date_and_id_val = statementService.getStatementHeaderIdAndLatestDate();
//       String db_id = db_date_and_id_val.split("@")[0];
//       String new_id = String.valueOf(Integer.parseInt(db_id) + 1);
//
//       return new OracleStatement(
//               new_id,
//               s.getACCOUNT_NUMBER(),
//               s.getSTATEMENT_PERIOD(),
//               "Zemen Bank",
//               "Abinet Branch",
//               s.getOPENING_BALANCE(),
//               s.getCLOSING_BALANCE(),
//               RECORD_STATUS_FLAG,
//               s.getACCOUNT_CCY(),
//               ORG_ID
//       );
//   }



    public OracleStatement get_oracle_statement(Statement s){
        String db_date_and_id_val = statementService.getStatementHeaderIdAndLatestDate();
        String db_id = db_date_and_id_val.split("@")[0];
        String parsed_id = db_id.replaceAll(KEEP_ONLY_DIGIT, "");
        String new_id = String.valueOf(Integer.parseInt(parsed_id) + 1);

        /*
        * change, add "i" value in the statement id field new id will be like 10005i
        * i stands for interface
        * */

        return new OracleStatement(
                new_id + "i",
                s.getACCOUNT_NUMBER(),
                s.getSTATEMENT_PERIOD().split(" - ")[0],
                "Zemen Bank",
                "Abinet Branch",
                s.getOPENING_BALANCE(),
                s.getCLOSING_BALANCE(),
                RECORD_STATUS_FLAG,
                s.getACCOUNT_CCY(),
                ORG_ID
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

    private String creditOrDebit(Double creditAmount){
       int credit_amt = (int) Math.round(creditAmount);
       if(credit_amt != 0){
           return CREDIT_VALUE_CODE;
       }
       return DEBIT_VALUE_CODE;
    }

    private Double getCreditOrDebitAmount(Double creditAmount, Double debitAmount){
        int credit_amt = (int) Math.round(creditAmount);
       if(credit_amt != 0){
           return creditAmount;
       }
       return debitAmount;
    }

    private String format_date(String date){
        OffsetDateTime offsetDateTime = OffsetDateTime.parse(date);
        return offsetDateTime.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    }
}
