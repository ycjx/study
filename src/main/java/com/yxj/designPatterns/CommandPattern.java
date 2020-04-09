package com.yxj.designPatterns;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-04-03 18:36
 */
public class CommandPattern {

    public static void main(String[] args) {
        CommandPattern commandPattern = new CommandPattern();
        commandPattern.test();
    }
    // This test method is a client
    public void test() {
        Administrator admin = new Administrator();
        Server server = new Server();

        // start Apache
        admin.setCommand(new StartApacheCommand(server));
        admin.typeEnter();

        // start Tomcat
        admin.setCommand(new StartTomcatCommand(server));
        admin.typeEnter();

        // check executed commands
        int executed = server.getExecutedCommands().size();
        System.out.println(executed);
    }

}


// commands
abstract class ServerCommand {

    protected Server server;

    public ServerCommand(Server server) {
        this.server = server;
    }

    public abstract void execute();
}

class StartTomcatCommand extends ServerCommand {

    public StartTomcatCommand(Server server) {
        super(server);
    }

    @Override
    public void execute() {
        server.launchCommand("sudo service tomcat7 start");
    }
}

class StartApacheCommand extends ServerCommand {

    public StartApacheCommand(Server server) {
        super(server);
    }

    @Override
    public void execute() {
        server.launchCommand("sudo service apache2 start");
    }
}

// invoker
class Administrator {

    private ServerCommand command;

    public void setCommand(ServerCommand command) {
        this.command = command;
    }

    public void typeEnter() {
        this.command.execute();
    }

}

// receiver
class Server {

    // as in common terminals, we store executed commands in history
    private List<String> executedCommands = new ArrayList<String>();

    public void launchCommand(String command) {
        System.out.println("Executing: "+command+" on server");
        this.executedCommands.add(command);
    }

    public List<String> getExecutedCommands() {
        return this.executedCommands;
    }

}
