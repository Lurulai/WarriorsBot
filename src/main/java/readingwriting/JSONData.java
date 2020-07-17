package readingwriting;

import cattouser.User;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

public class JSONData {
    // All the registered users (temporary until stored as a json)
    public static HashMap<String, ArrayList<User>> registeredUsers = new HashMap<>();

    public static void updateUser(User user, String guildID) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Path workingDir = Paths.get(System.getProperty("user.dir"));
        File guildDir = new File(workingDir.resolve("database/servers/" + guildID).toUri());
        if (!guildDir.exists()) {
            guildDir.mkdirs();
        }
        File outFile = new File(guildDir, user.getUserID()+".json");
        try {
            if(outFile.exists()){
                Files.move(Paths.get(outFile.getAbsolutePath()), Paths.get(new File(guildDir, user.getUserID()+"-backup.json").getAbsolutePath()), StandardCopyOption.ATOMIC_MOVE);
            }
            outFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Couldn't create " + outFile.getName() + " file at " + outFile.getAbsolutePath());
            e.printStackTrace();
            return;
        }

        if(user.getCattos().size() == 0){
            outFile.delete();
        }
        else {
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            try {
                writer.writeValue(outFile, user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static User getUser(String id, String guildID) {
        ObjectMapper mapper = new ObjectMapper();
        User u = null;

        Path workingDir = Paths.get(System.getProperty("user.dir"));
        File guildDir = new File(workingDir.resolve("database/servers/" + guildID).toUri());
        if (!guildDir.exists()) {
            guildDir.mkdirs();
        }
        File outFile = new File(guildDir, id+".json");
        try {
            u = mapper.readValue(outFile, User.class);
        } catch (IOException e) {
            System.out.println("Couldn't create " + outFile.getName() + " file at " + outFile.getAbsolutePath());
            e.printStackTrace();
        }
        return u;
    }

    public static void loadAllUsers(){
        String[] folders = new File(System.getProperty("user.dir")+"/database/servers").list();
        if(folders == null){
            return;
        }
        for (String guildID : folders) {
            String[] files = new File(System.getProperty("user.dir") + "/database/servers/" + guildID).list();
            if (files == null) {
                return;
            }
            ArrayList<User> users = new ArrayList<>();
            for (String userID : files) {
                users.add(getUser(userID.replaceAll("[^0-9]", ""), guildID));
            }
            registeredUsers.put(guildID.replaceAll("[^0-9]", ""), users);
        }
    }
}