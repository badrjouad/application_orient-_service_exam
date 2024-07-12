package dauphine.eu.bank_account.controller;


import dauphine.eu.bank_account.model.Account;
import dauphine.eu.bank_account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public Account registerAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account accountDetails) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        account.setEmail(accountDetails.getEmail());
        return accountRepository.save(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        Account account = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        accountRepository.delete(account);
        return ResponseEntity.noContent().build();
    }

}

