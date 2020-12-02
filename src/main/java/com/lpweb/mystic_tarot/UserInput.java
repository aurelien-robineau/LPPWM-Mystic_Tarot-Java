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
            Integer cardNumber = this.getInteger(label);
            if (CardManager.getInstance().cardNumberExists(cardNumber)) {
                return cardNumber;
            }

            System.err.println("\"" + label + "\" must be a existing card number.");
        }
    }
}
