package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<Integer, String> commonMap = new HashMap<>(previous.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName
                )));
        commonMap.putAll(current.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        User::getName
                )));
        added = Math.max(commonMap.size() - previous.size(), 0);
        for (User user : previous) {
            if (commonMap.containsKey(user.getId())
                    && !commonMap.get(user.getId()).equals(user.getName())) {
                changed++;
            }
            if (!current.stream()
                    .map(User::getId)
                    .collect(Collectors.toSet()).contains(user.getId())) {
                deleted++;
            }
        }
        return new Info(added, changed, deleted);
    }
}