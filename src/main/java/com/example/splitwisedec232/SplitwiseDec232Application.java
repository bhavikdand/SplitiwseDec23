package com.example.splitwisedec232;

import com.example.splitwisedec232.commands.Command;
import com.example.splitwisedec232.commands.CommandRegistry;
import com.example.splitwisedec232.exceptions.InvalidCommandException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;
import java.util.Scanner;

@SpringBootApplication
public class SplitwiseDec232Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SplitwiseDec232Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Awaiting input");
            String input = scanner.nextLine();

            Optional<Command> optionalCommand = CommandRegistry.getInstance().get(input);
            if(optionalCommand.isEmpty()){
                System.out.println("Please give a correct command as input");
                continue;
            }
            Command command = optionalCommand.get();
            try {
                command.execute(input);
            } catch (InvalidCommandException ice){
                System.out.println("Error:" + ice.getMessage());
            }


        }
    }
}
