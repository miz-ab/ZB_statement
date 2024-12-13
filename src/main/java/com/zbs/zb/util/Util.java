package com.zbs.zb.util;

import com.zbs.zb.model.Bank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.atomic.AtomicReference;

import static com.zbs.zb.constants.Constants.NOT_FOUND;
import static com.zbs.zb.constants.Constants.NOT_FOUND_INT;

@Slf4j
@Service
public class Util {

    @Autowired
    private JdbcTemplate secondaryJdbcTemplate;

    public Integer getBankBranchId(String accountNumber) {
        AtomicReference<Integer> branchId = new AtomicReference<>(NOT_FOUND_INT);
        try{
            String sql = "SELECT BANK_BRANCH_ID FROM CE_BANK_ACCOUNTS WHERE BANK_ACCOUNT_NUM = ?";

            secondaryJdbcTemplate.query(sql, new Object[]{accountNumber}, rs -> {
                branchId.set(rs.getInt("BANK_BRANCH_ID"));
            });
            return branchId.get();
        }catch (Exception e){
            log.error("error get branch ID {}", e.getMessage());
        }
        return branchId.get();
    }

    public Bank getBankNameBranch(String bankBranchId){
        Bank bank = new Bank();
        try{
            String sql = "SELECT BANK_BRANCH_NAME, BANK_NAME FROM CE_BANK_BRANCHES_V WHERE BRANCH_PARTY_ID = ?";
            secondaryJdbcTemplate.query(sql, new Object[]{bankBranchId}, rs -> {
                bank.setBankName(rs.getString("BANK_NAME"));
                bank.setBankBranchName(rs.getString("BANK_BRANCH_NAME"));
            });
        }catch (Exception e){
            log.error("error get branch Name {}", e.getMessage());
        }
        return bank;
    }


    }

