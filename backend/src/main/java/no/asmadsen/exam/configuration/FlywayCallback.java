package no.asmadsen.exam.configuration;

import com.github.javafaker.Faker;
import no.asmadsen.exam.entity.Movie;
import org.flywaydb.core.api.callback.Callback;
import org.flywaydb.core.api.callback.Context;
import org.flywaydb.core.api.callback.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.flywaydb.core.api.callback.Event.AFTER_EACH_MIGRATE;

@Configuration
public class FlywayCallback implements Callback {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private Faker faker = new Faker();

    @Override
    public boolean supports(Event event, Context context) {
        return event == AFTER_EACH_MIGRATE;
    }

    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return false;
    }

    @Override
    public void handle(Event event, Context context) {
        if (event == AFTER_EACH_MIGRATE) {
            Connection connection = context.getConnection();
            switch (context.getMigrationInfo().getVersion().getVersion()) {
                case "1.0":
                    createUser(connection);
                    createUser(connection);
                    createUser(connection);
                    createUser(connection);
                    createUser(connection);
                    createUser(connection);
                    createUser(connection);
                    createUser(connection);
                    createUser(connection);
                    createUser(connection);
                    break;
            }
        }
    }

    private void createUser(Connection connection) {
        try {
            PreparedStatement statement = connection
                    .prepareStatement(
                            "insert into users (id, email, password, alias, first_name, last_name, enabled) values (?, ?, ?, ?, ?, ?, ?)");

            UUID uuid = UUID.randomUUID();
            statement.setObject(1, uuid);
            statement.setString(2, faker.internet().emailAddress());
            statement.setString(3, passwordEncoder.encode("Password"));
            statement.setString(4, faker.name().username());
            statement.setString(5, faker.name().firstName());
            statement.setString(6, faker.name().lastName());
            statement.setBoolean(7, true);
            statement.execute();
            statement = connection
                    .prepareStatement("insert into user_roles (user_id, roles) values (?, 'USER')");

            statement.setObject(1, uuid);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
