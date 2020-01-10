package com.example.tinbackend.security.endpoint;

import com.example.tinbackend.security.domain.expense.dto.AddExpenseDto;
import com.example.tinbackend.security.domain.expense.dto.EditExpenseDto;
import com.example.tinbackend.security.domain.expense.dto.ExpenseDto;
import com.example.tinbackend.security.domain.expense.service.ExpenseService;
import com.example.tinbackend.security.domain.usertravelgroup.dto.UserGroupSettledBalanceDto;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/expense")
public class ExpenseEndpoint {

    private final ExpenseService expenseService;

    public ExpenseEndpoint(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/{id}")
    public ExpenseDto getExpenseById(@PathVariable("id") Long id) {
        return expenseService.findExpenseById(id);
    }

    @GetMapping("")
    public List<ExpenseDto> getAllExpenses() {
        return expenseService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable("id") Long id) {
        expenseService.deleteById(id);
    }

    @PutMapping("")
    public ExpenseDto editExpense(@Valid @RequestBody EditExpenseDto editExpenseDto){
        return expenseService.editExpense(editExpenseDto);
    }

    @PostMapping("")
    public ExpenseDto addExpense(@Valid @RequestBody AddExpenseDto addExpenseDto){
        return expenseService.addExpense(addExpenseDto);
    }

    @GetMapping("/group/{groupId}")
    public List<ExpenseDto> getExpensesByGroupId(@PathVariable("groupId") Long groupId){
        return expenseService.getExpensesByGroupId(groupId);
    }

    @GetMapping("/balance/{username}")
    public List<UserGroupSettledBalanceDto> getGroupExpensesByUserId(@PathVariable("username") String username){
       return expenseService.getGroupExpensesByUsername(username);
    }
}
