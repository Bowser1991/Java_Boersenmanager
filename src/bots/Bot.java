package bots;

import Exception.BotException;

public interface Bot {
	void start(String playername)throws BotException;
	void doAction();
	void stop(String playername) throws BotException;
}
