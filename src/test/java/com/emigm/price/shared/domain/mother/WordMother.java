package com.emigm.price.shared.domain.mother;

public final class WordMother {
  public static String random() {
    return MotherCreator.random().lorem().word();
  }
}
