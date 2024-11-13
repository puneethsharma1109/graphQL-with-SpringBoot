package com.puneeth.graphQL.service;

import com.puneeth.graphQL.model.Player;
import com.puneeth.graphQL.model.Team;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PlayerService {

    private List<Player> players = new ArrayList<>();

    AtomicInteger id = new AtomicInteger(0);

    public List<Player> findAll() {
        return players;
    }

    public Optional<Player> findOne(Integer id) {
        return players.stream().filter(player -> player.id() == id).findFirst();
    }

    public Player create(String name, Team team) {
        Player player = new Player(id.getAndIncrement(), name, team);
        players.add(player);
        return player;
    }

    public Player delete(Integer id) {
        Player player = players.stream().filter(c -> c.id() == id)
                .findFirst().orElseThrow(() -> new IllegalArgumentException());
        players.remove(player);
        return player;
    }

    public Player update(Integer id, String name, Team team) {
        Player updatedPlayer = new Player(id, name, team);
        Optional<Player> optionalPlayer = players.stream().filter(c -> c.id() == id).findFirst();
        if (optionalPlayer.isPresent()) {
            Player player = optionalPlayer.get();
            int index = players.indexOf(player);
            players.set(index, updatedPlayer);
        } else {
            throw new IllegalArgumentException("Invalid Player");
        }
        return updatedPlayer;
    }

    @PostConstruct
    private void init() {
        players.add(new Player(id.getAndIncrement(), "MS Dhoni", Team.CSK));
        players.add(new Player(id.getAndIncrement(), "Ruturaj Gaikwad", Team.CSK));
        players.add(new Player(id.getAndIncrement(), "Rohit Sharma", Team.MI));
        players.add(new Player(id.getAndIncrement(), "Jasprit Bumrah", Team.MI));
        players.add(new Player(id.getAndIncrement(), "Virat Kohli", Team.RCB));
        players.add(new Player(id.getAndIncrement(), "Rishabh Pant", Team.DC));
        players.add(new Player(id.getAndIncrement(), "Rashid Khan", Team.GT));
        players.add(new Player(id.getAndIncrement(), "Andre Russel", Team.KKR));
    }
}
