package commands;

import cattos.*;
import cattouser.User;
import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import java.util.ArrayList;

public class CreateCatto extends Command {
    ArrayList<User> registeredUsers = new ArrayList<>();

    public CreateCatto() {
        this.name = "createcat";
        this.aliases = new String[]{"create"};
//        this.category = new Category("Informative");
        this.ownerCommand = true;
    }

    // .createcat name age class subclass

    @Override
    protected void execute(CommandEvent event) {
        String[] args = event.getArgs().split(" ");
        if(args.length < 4){
            event.getTextChannel().sendMessage("Not enough arguments.").queue();
            return;
        }

        User user = null;
        boolean userFound = false;
        for(User u : registeredUsers){
            if(u.getUserID().equals(event.getAuthor().getId())){
                user = u;
                userFound = true;
                break;
            }
        }
        if(!userFound){
            user = new User(event.getAuthor().getId());
        }

        boolean isValid = false;
        switch(args[2].toLowerCase()){
            case "warrior":
                Warrior warrior = new Warrior(args[0]);
                warrior.setAge(Integer.parseInt(args[1]));
                isValid = warrior.setSubClass(args[3]);
                if(isValid) user.addCatto(warrior);
                break;
            case "apprentice":
                Apprentice apprentice = new Apprentice(args[0]);
                apprentice.setAge(Integer.parseInt(args[1]));
                isValid = apprentice.setSubClass(args[3]);
                if(isValid) user.addCatto(apprentice);
                break;
            case "medicine":
                Medicine medicine = new Medicine(args[0]);
                medicine.setAge(Integer.parseInt(args[1]));
                isValid = medicine.setSubClass(args[3]);
                if(isValid) user.addCatto(medicine);
                break;
            case "misc":
                Misc misc = new Misc(args[0]);
                misc.setAge(Integer.parseInt(args[1]));
                isValid = misc.setSubClass(args[3]);
                if(isValid) user.addCatto(misc);
                break;
            case "kit":
                Kit kit = new Kit(args[0]);
                kit.setAge(Integer.parseInt(args[1]));
                isValid = kit.setSubClass(args[3]);
                if(isValid) user.addCatto(kit);
                break;
        }

        if(isValid){
            event.getTextChannel().sendMessage(event.getAuthor().getAsMention() + ", your " + args[2] + " class and " + args[3] + " subclass catto with name " + args[0] + " and age " + args[1] + " moons has been materialized." ).queue();
        }
        else{
            event.getTextChannel().sendMessage("Fucka uuu").queue();
        }

    }
}
