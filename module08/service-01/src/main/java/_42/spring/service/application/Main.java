package _42.spring.service.application;

import _42.spring.service.models.User;
import _42.spring.service.repositories.UsersRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        System.out.println("========================================");
        System.out.println("   Testing UsersRepositoryJdbcImpl");
        System.out.println("========================================");
        testRepository(context.getBean("usersRepositoryJdbc", UsersRepository.class));

        System.out.println("\n========================================");
        System.out.println("   Testing UsersRepositoryJdbcTemplateImpl");
        System.out.println("========================================");
        testRepository(context.getBean("usersRepositoryJdbcTemplate", UsersRepository.class));
    }

    private static void testRepository(UsersRepository repo) {

        // ── findAll (initial state) ──────────────────────────
        System.out.println("\n[findAll] Initial:");
        repo.cleanAll();
        System.out.println(repo.findAll());

        // ── save ────────────────────────────────────────────
        System.out.println("\n[save] Inserting 2 users...");
        repo.save(new User("alice@gmail.com"));
        repo.save(new User("bob@gmail.com"));
        System.out.println("[findAll] After save:");
        System.out.println(repo.findAll());

        // ── findByEmail ──────────────────────────────────────
        System.out.println("\n[findByEmail] Looking for alice@gmail.com:");
        System.out.println(repo.findByEmail("alice@gmail.com"));
        System.out.println("[findByEmail] Looking for unknown@gmail.com:");
        System.out.println(repo.findByEmail("unknown@gmail.com"));

        // ── findById ─────────────────────────────────────────
        System.out.println("\n[findById] Looking for id = 1:");
        System.out.println(repo.findById(1L));
        System.out.println("[findById] Looking for id = 9999 (should be null):");
        System.out.println(repo.findById(9999L));

        // ── update ───────────────────────────────────────────
        System.out.println("\n[update] Changing email of user id=1 to updated@gmail.com:");
        User toUpdate = repo.findById(1L);
        if (toUpdate != null) {
            toUpdate.setEmail("updated@gmail.com");
            repo.update(toUpdate);
            System.out.println("[findById] After update:");
            System.out.println(repo.findById(1L));
        } else {
            System.out.println("No user with id=1 found, skipping update.");
        }

        // ── delete ───────────────────────────────────────────
        System.out.println("\n[delete] Deleting user id=1:");
        repo.delete(1L);
        System.out.println("[findAll] After delete:");
        System.out.println(repo.findAll());
    }
}