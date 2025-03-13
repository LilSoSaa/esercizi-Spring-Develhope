package com.example.esercizi_develhope_sito.EserciziDevelhopeSito.Controller;

import com.develhope.demo.EserciziDevelhopeSito.Entities.User;
import com.develhope.demo.EserciziDevelhopeSito.RepositoryEx.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/*Crea un'applicazione Spring Boot con Controller v3/ciao
che legge un dato nel database e lo restituisce nel body della chiamata.*/

@RestController
@RequestMapping("/v3/ciao")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // 📌 POST - Aggiungi un nuovo utente
    @PostMapping
    public ResponseEntity<User> addUser (@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    // 📌 GET - Ottieni un utente tramite ID
    @GetMapping("/{id}")
    public Optional<User> getUserById (@PathVariable long id) {
        return userRepository.findById(id);

    }

    // 📌 GET - Ottieni tutti gli utenti
    @GetMapping("/all}")
    public List<User> getAllUsers () {
        return userRepository.findAll();

    }

    // 📌 GET - Saluto semplice (nome e provincia via query param)
    @GetMapping("/saluto")
    public String saluto (@RequestParam (required = false, defaultValue = "") String nome,
                          @RequestParam  String provincia) {
        return "Ciao " + nome + " com'è il tempo in " + provincia + "?";
    }


    // 📌 GET - Ritorna un User con nome e provincia (path + query param)
    @GetMapping("/provincia/{provincia}")
    public User getSalutoANDProvincia (@PathVariable String provincia,
                                         @RequestParam(required = false, defaultValue = "Anonimo") String nome) {
        String messaggio = "Ciao " + nome + ", com'è il tempo in " + provincia + "?";
        return new User(nome, provincia, messaggio);
    }


    // 📌 GET - Ritorna un Map con i dettagli del saluto
    @GetMapping("/info/{provincia}")
    public Map<String, String> salutoMap (@RequestParam String nome, @PathVariable String provincia) {
        Map<String, String> salutoMap = new HashMap<>();
        salutoMap.put("nome", nome);
        salutoMap.put("provincia", provincia);
        String saluto = "Ciao " + nome + ", com'è il tempo in " + provincia;
        salutoMap.put("saluto", saluto);

        return salutoMap;
    }


    // 📌 DELETE - Rimuove un utente tramite ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser (@PathVariable long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
