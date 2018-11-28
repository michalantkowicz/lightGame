package com.mantkowicz.light.service.phrase;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mantkowicz.light.service.phrase.PhraseGroup.DARKNESS_EXCLAMATION;

public class PhraseService {
    private final Map<PhraseGroup, List<String>> phrases;

    public PhraseService() {
        this.phrases = new HashMap<>();
        for (PhraseGroup values : PhraseGroup.values()) {
            phrases.put(DARKNESS_EXCLAMATION, DARKNESS_EXCLAMATION.getValues());
        }
    }

    public String getRandomPhrase(PhraseGroup phraseGroup) {
        List<String> phrasesOfGroup = phrases.get(phraseGroup);
        Collections.shuffle(phrasesOfGroup);
        return phrasesOfGroup.get(0);
    }
}
