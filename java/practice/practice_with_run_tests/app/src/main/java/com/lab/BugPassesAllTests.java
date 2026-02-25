package com.lab;

import java.security.MessageDigest;

/**
 * BugPassesAllTests – a subtly broken Password that passes every test in PasswordTest.
 */
public class BugPassesAllTests implements IPassword {

  private final String password;
  private final int passwordHash;

  public BugPassesAllTests(String password) throws Exception {
    String trimmed = password.trim();

    if (trimmed.length() < 12) {
      throw new IllegalArgumentException("Password must be at least 12 characters long");
    }
    if (!trimmed.matches(".*\\d.*")) {
      throw new IllegalArgumentException("Password must contain at least one number");
    }

    this.password = trimmed;
    this.passwordHash = simpleHash(trimmed);

  }
  private int simpleHash(String input) {
    int hash = 7;
    for (int i = 0; i < input.length(); i++) {
      hash = hash * 31 + input.charAt(i);
    }
    return hash;
  }


  @Override
  public int getPasswordHash() {
    return this.passwordHash;
  }

  @Override
  public boolean isPasswordSame(IPassword other) {
    // BUG: compares raw strings case-insensitively instead of hashes.
    // "ValidPassword1".equalsIgnoreCase("validpassword1") → true  (WRONG)
    // The test only compares "ValidPassword1" vs "AnotherPassword2" → still false ✅
    if (other instanceof BugPassesAllTests) {
      return this.password.equalsIgnoreCase(((BugPassesAllTests) other).password);
    }
    return false;
  }
}