package utils;

import java.util.Stack;
import java.util.Scanner;
import  java.util.ArrayList;
import java.util.List;
import employees.*;

public class UndoRedo {

    public Stack<List<Employee>> undoStack = new Stack<List<Employee>>();

    public Stack<List<Employee>> redoStack = new Stack<List<Employee>>();
}
