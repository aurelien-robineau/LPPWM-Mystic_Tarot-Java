package com.lpweb.mystic_tarot;

import java.util.Scanner;

import com.lpweb.mystic_tarot.card.CardManager;

public class UserInput {
    public UserInput () {}

    //--------------------------------------------------------------------------
    // Public methods
    //--------------------------------------------------------------------------

    /**
     * Asks the user to enter a value and return it as a String.
     * @param label the name of the input.
     * @return the value of the input.
     */
    public String getString(String label) {
        Scanner input = MysticTarot.getScanner();
        System.out.print(label + ": ");
        return input.nextLine();
    }

    /**
     * Asks the user to enter a value and return it as a Integer.
     * Method will keep asking the user to enter a value while it is not an 
     * int (using the {@code Integer.parseInt()} method).
     * @param label the name of the input
     * @return the value of the input
     */
    public Integer getInteger(String label) {
        Scanner input = MysticTarot.getScanner();

        while (true) {
            System.out.print(label + ": ");

            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.err.println("\"" + label + "\" must be an integer");
            }
        }
    }

    /**
     * Asks the user to enter a value and return it as a Integer by using the
     * {@code UserInput.getInteger()} method.
     * Method will keep asking the user to enter a value while it is not an 
     * existing card number.
     * @param label the name of the input
     * @return the value of the input
     */
    public Integer getCardNumber(String label) {
        while (true) {
            Integer cardNumber = getInteger(label);
            if (CardManager.getInstance().cardNumberExists(cardNumber)) {
                return cardNumber;
            }

            System.err.println("\"" + label + "\" must be a existing card number.");
        }
    }
    
    /**
     * Asks the user to enter a value and return it as a Integer by using the
     * {@code UserInput.getInteger()} method.
     * Method will keep asking the user to enter a value while this is an
     * already existing card number.
     * @param label the name of the input
     * @param exclude a valid card number even if it already exits
     * @return the value of the input
     */
    public Integer getNewCardNumber(String label, Integer exclude) {
        while (true) {
            Integer cardNumber = getInteger(label);
            Boolean numberExists = CardManager.getInstance().cardNumberExists(cardNumber);
            if (cardNumber.equals(exclude) || !numberExists) {
                return cardNumber;
            }

            System.err.println("\"" + label + "\" is already a card number.");
        }
    }

    /**
     * This method allows to use {@code UserInput.getNewCardNumber()} without
     * excluding a number.
     * @param label the name of the input
     * @return the value of the input
     * @see UserInput#getNewCardNumber(String, Integer) getNewCardNumber
     */
    public Integer getNewCardNumber(String label) {
        return getNewCardNumber(label, null);
    }
}
