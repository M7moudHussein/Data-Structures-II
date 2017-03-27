package eg.edu.alexu.csd.filestructure.avl.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.IDictionary;

public class Dictionary implements IDictionary {
    private IAVLTree<String> avlSearchTree;
    private Integer sizeOfDict;

    public Dictionary() {
	avlSearchTree = new AVLTree<String>();
	sizeOfDict = 0;
    }

    @Override
    public void load(File file) {
	try {
	    FileReader fileReader = new FileReader(file);
	    BufferedReader bufferedReader = new BufferedReader(fileReader);
	    String word;
	    word = bufferedReader.readLine();
	    while (word != null) {
		insert(word);
		word = bufferedReader.readLine();
	    }
	    bufferedReader.close();
	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public boolean insert(String word) {
	if (avlSearchTree.search(word)) {
	    return false;
	}
	sizeOfDict++;
	avlSearchTree.insert(word);
	return true;
    }

    @Override
    public boolean exists(String word) {
	return avlSearchTree.search(word);
    }

    @Override
    public boolean delete(String word) {
	if (avlSearchTree.delete(word)) {
	    sizeOfDict--;
	    return true;
	}
	return false;
    }

    @Override
    public int size() {
	return sizeOfDict;
    }

    @Override
    public int height() {
	return avlSearchTree.height();
    }

}
