package edu.hw10.Task2.Fact;

import edu.hw10.Task2.Annotation.Cache;

public interface Palindrome {
    @Cache(persist = true)
    public boolean isPalindrome(String s);
}
