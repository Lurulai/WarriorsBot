package commands;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

public class DeleteCatto extends Command {
    /**
     * Initialize the .create command along with the event waiter
     * @param event event waiter class
     */
    public DeleteCatto(EventWaiter event) {
        this.name = "createcat";
        this.aliases = new String[]{"create"};
//        this.category = new Category("Informative");
        this.ownerCommand = false;
    }

    @Override
    protected void execute(CommandEvent event) {

    }
}
