package nl.hypothermic.javacogs.handlers;

public abstract class Handler<HANDLER extends IHandler> {
	
    public abstract Class<HANDLER> getHandler();

    public static final Handler<DatabaseHandler> DATABASE = new Handler<DatabaseHandler>() {
        @Override
        public Class<DatabaseHandler> getHandler() {
            return DatabaseHandler.class;
        }
    };
}