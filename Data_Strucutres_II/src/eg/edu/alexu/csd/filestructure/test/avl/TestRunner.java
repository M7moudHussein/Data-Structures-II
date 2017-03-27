package eg.edu.alexu.csd.filestructure.test.avl;

import eg.edu.alexu.csd.filestructure.avl.*;
import eg.edu.alexu.csd.filestructure.avl.impl.*;

public class TestRunner {

    public static IAVLTree getImplementationInstance() {
	return new AVLTree();
    }

    public static IDictionary getDicImplementationInstance() {
	return new Dictionary();
    }

}
