package xyz.fiire.onlysmp.utils.SQLiteLib.Database;

import xyz.fiire.onlysmp.utils.SQLiteLib.Error;
import xyz.fiire.onlysmp.utils.SQLiteLib.Errors;
import xyz.fiire.onlysmp.utils.SQLiteLib.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public abstract class Database {
    protected Connection connection;

    public Database() {
    }

    public abstract Connection getSQLConnection();

    public abstract void load();

    public void initialize() {
        this.connection = this.getSQLConnection();

        try {
            PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM test");
            ResultSet rs = ps.executeQuery();
            this.close(ps, rs);
        } catch (SQLException var3) {
            Main.getInstance().getLogger().log(Level.SEVERE, "Unable to retreive connection", var3);
        }

    }

    public Boolean executeStatement(String statement) {
        Connection conn = null;
        PreparedStatement ps = null;

        Boolean var6;
        try {
            conn = this.getSQLConnection();
            ps = conn.prepareStatement(statement);
            var6 = !ps.execute();
            return var6;
        } catch (SQLException var14) {
            Main.getInstance().getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), var14);
            var6 = false;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var13) {
                Main.getInstance().getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), var13);
                return false;
            }

        }

        return var6;
    }

    public Object queryValue(String statement, String row) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Object var8;
        try {
            conn = this.getSQLConnection();
            ps = conn.prepareStatement(statement);
            rs = ps.executeQuery();
            if (!rs.next()) {
                return null;
            }

            var8 = rs.getObject(row);
        } catch (SQLException var17) {
            Main.getInstance().getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), var17);
            return null;
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var16) {
                Main.getInstance().getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), var16);
            }

        }

        return var8;
    }

    public List<Object> queryRow(String statement, String row) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList objects = new ArrayList();

        try {
            conn = this.getSQLConnection();
            ps = conn.prepareStatement(statement);
            rs = ps.executeQuery();

            while (rs.next()) {
                objects.add(rs.getObject(row));
            }

            ArrayList var9 = objects;
            return var9;
        } catch (SQLException var17) {
            Main.getInstance().getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), var17);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var16) {
                Main.getInstance().getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), var16);
            }

        }

        return null;
    }

    public Map<String, List<Object>> queryMultipleRows(String statement, String... row) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Object> objects = new ArrayList();
        HashMap map = new HashMap();

        try {
            conn = this.getSQLConnection();
            ps = conn.prepareStatement(statement);
            rs = ps.executeQuery();

            while (rs.next()) {
                String[] var11 = row;
                int var10 = row.length;

                String singleRow;
                int var9;
                for (var9 = 0; var9 < var10; ++var9) {
                    singleRow = var11[var9];
                    objects.add(rs.getObject(singleRow));
                }

                var11 = row;
                var10 = row.length;

                for (var9 = 0; var9 < var10; ++var9) {
                    singleRow = var11[var9];
                    map.put(singleRow, objects);
                }
            }

            HashMap var13 = map;
            return var13;
        } catch (SQLException var21) {
            Main.getInstance().getLogger().log(Level.SEVERE, Errors.sqlConnectionExecute(), var21);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException var20) {
                Main.getInstance().getLogger().log(Level.SEVERE, Errors.sqlConnectionClose(), var20);
            }

        }

        return null;
    }

    public void close(PreparedStatement ps, ResultSet rs) {
        try {
            if (ps != null) {
                ps.close();
            }

            if (rs != null) {
                rs.close();
            }
        } catch (SQLException var4) {
            Error.close(Main.getInstance(), var4);
        }

    }

    public void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException var2) {
            Error.close(Main.getInstance(), var2);
        }

    }
}
