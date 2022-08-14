package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = properties.getProperty("hibernate.connection.url");
        String login = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            this.connection = connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void statementExecute(String sqlFormat) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(sqlFormat);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        statementExecute(String.format(
                "create table if not exists %s(%s, %s);",
                tableName,
                "id serial primary key",
                "name text")
        );
    }

    public void dropTable(String tableName) {
        statementExecute(String.format(
                    "drop table if exists %s;",
                    tableName)
        );
    }

    public void addColumn(String tableName, String columnName, String type) {
        statementExecute(String.format(
                    "alter table if exists %s add %s %s;",
                    tableName,
                    columnName,
                    type)
        );
    }

    public void dropColumn(String tableName, String columnName) {
        statementExecute(String.format(
                    "alter table if exists %s drop column %s;",
                    tableName,
                    columnName)
        );
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        statementExecute(String.format(
                    "alter table if exists %s rename column %s to %s;",
                    tableName,
                    columnName,
                    newColumnName)
            );
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("jdbc.properties")) {
            config.load(in);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        TableEditor tableEditor = new TableEditor(config);

        tableEditor.createTable("tabl");
        System.out.println(getTableScheme(tableEditor.connection, "tabl"));

        tableEditor.addColumn("tabl", "new_column", "int");
        System.out.println(getTableScheme(tableEditor.connection, "tabl"));

        tableEditor.renameColumn("tabl", "new_column", "new_column_v2");
        System.out.println(getTableScheme(tableEditor.connection, "tabl"));

        tableEditor.dropColumn("tabl", "new_column_v2");
        System.out.println(getTableScheme(tableEditor.connection, "tabl"));

        tableEditor.dropTable("tabl");

        tableEditor.close();
    }
}
