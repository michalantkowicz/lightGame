package com.mantkowicz.light.service.phrase;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhraseService {
    private final Map<PhraseGroup, List<String>> phrases;

    public PhraseService() {
        this.phrases = new HashMap<>();
        for (PhraseGroup phraseGroup : PhraseGroup.values()) {
            phrases.put(phraseGroup, phraseGroup.getValues());
        }
    }

    public String getRandomPhrase(PhraseGroup phraseGroup) {
        List<String> phrasesOfGroup = phrases.get(phraseGroup);
        Collections.shuffle(phrasesOfGroup);
        return phrasesOfGroup.get(0);
    }

    public String getPhrase(PhraseGroup phraseGroup) {
        return phrases.get(phraseGroup).get(0);
    }
}
