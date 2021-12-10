package ru.job4j.question;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> previousMap = previous.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName
                ));
        Map<Integer, String> currentMap = current.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName
                ));
        Set<Integer> commonSetId = new HashSet<>(previousMap.keySet());
        commonSetId.addAll(currentMap.keySet());
        added = Math.max(commonSetId.size() - previous.size(), 0);
        for (Integer id : previousMap.keySet()) {
            if (!currentMap.containsKey(id)) {
                deleted++;
            } else if (!currentMap.get(id).equals(previousMap.get(id))) {
                changed++;
            }
        }
        return new Info(added, changed, deleted);
    }
}