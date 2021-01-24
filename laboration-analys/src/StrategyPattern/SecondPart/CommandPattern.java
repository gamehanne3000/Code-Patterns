package StrategyPattern.SecondPart;
import StrategyPattern.FirstPart.StandAloneClass.ShoppingCart;

import java.util.Stack;

/*
    * A command is an object whose role is to store all the information required for
    * executing an action, including the method to call, the method arguments,
    * and the object (known as the receiver) that implements the method.
*/
interface Command {
    void execute();
}

// The two class beneath is concrete actions
class UndoCommand implements Command {
    private Receiver receiver;

    public UndoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.undo();
    }
}

class RedoCommand implements Command {
    private Receiver receiver;

    public RedoCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute() {
        receiver.redo();
    }
}

/*
    * Performs a set of cohesive actions
    * This component performs the actual action when the command's execute() method is called from the Invoker.
*/
class Receiver {

    private ShoppingCart action = new ShoppingCart();
    Invoker invoker = new Invoker();

    public void undo() {
        action.undo(invoker.undoStack);
    }

    public void redo() {
        action.redo(invoker.redoStack, invoker.undoStack);
    }
}

/*
    * Invoker class
*/
class Invoker {

    public Stack<Command> undoStack = new Stack<>();
    public Stack<Command> redoStack = new Stack<>();

    // Take an concrete action and add to the list
    public void undoComamnd(Command command){
        undoStack.add(command);
    }
    public void redoComamnd(Command command){
        redoStack.add(command);
    }

    // Take all redoCommands and execute all operations
    public void executeUndoCommand(){
        for (Command command: undoStack) {
            command.execute();
        }
    }

    // Take all executeRedoCommands and execute all operations
    public void executeRedoCommand(){
        for (Command command: redoStack) {
            command.execute();
        }
    }
}

/*
    * Client class
*/
public class CommandPattern {

    public static void main(String[] args) {

        // Initiates the Receiver class
        Receiver receiver = new Receiver();

        // Calling the concrete commands
        UndoCommand undoCommand = new UndoCommand(receiver);
        RedoCommand redoCommand = new RedoCommand(receiver);

        // Initiates the invoker class
        Invoker invoker = new Invoker();

        invoker.undoComamnd(undoCommand); // undo
        invoker.undoComamnd(undoCommand); // undo
        invoker.undoComamnd(undoCommand); // undo
        invoker.undoComamnd(undoCommand); // undo
        invoker.undoComamnd(undoCommand); // undo

        invoker.redoComamnd(redoCommand); // Redo
        invoker.redoComamnd(redoCommand); // Redo

        invoker.undoComamnd(undoCommand); // undo
        invoker.undoComamnd(undoCommand); // undo

        invoker.redoComamnd(redoCommand); // Redo
        invoker.redoComamnd(redoCommand); // Redo
        invoker.redoComamnd(redoCommand); // Redo

        //invoker.addCommand(undoCommand); // undo -> can still undo even if the is nothing to undo :(
        //invoker.addCommand(undoCommand); // undo
        //invoker.addCommand(undoCommand); // undo

        invoker.executeUndoCommand(); // Execute undo actions
        invoker.executeRedoCommand(); // Execute redo actions
    }
}