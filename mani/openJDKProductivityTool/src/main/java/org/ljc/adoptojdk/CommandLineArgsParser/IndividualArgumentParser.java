package org.ljc.adoptojdk.CommandLineArgsParser;

import static org.ljc.adoptojdk.ClassContributorRetriever.ClassContributorRetriever.*;

public class IndividualArgumentParser {
	
	private ArgumentsParsedResult parsedResult = new ArgumentsParsedResult();
	private String commandLineArgs = "";
	
	public IndividualArgumentParser(String commandLineArgs) {
		this.commandLineArgs = commandLineArgs.trim();		
		doParseCommandLineArgs(this.commandLineArgs);
	}

	private void doParseCommandLineArgs(String commandLineArgs) {
		if (commandLineArgs.equals("")) {
			parsedResult.setErrorMessage(getNoArgumentsPassedMessage());
		}
		else {
			// process arguments and display message
			if (argumentsAreValid(commandLineArgs)) {
				
				// CLASS OWNER SWITCH: -co
				if (commandLineArgs.toLowerCase().startsWith(CLASS_OWNER_SWITCH)) {
					String classNames;
					parsedResult.setCommandLineSwitch(CLASS_OWNER_SWITCH);
					try	{
						int startIndex = CLASS_OWNER_SWITCH.length();
						int endIndex = commandLineArgs.length();
						classNames = commandLineArgs.substring(startIndex, endIndex);
						classNames = classNames.trim();
					}
					catch (Exception e) {
						classNames = "";
						parsedResult.setErrorMessage("Error message: " + e.getMessage());
						System.out.println(parsedResult);
					}
					if ((classNames == null) || (classNames.equals(""))) {
						parsedResult.setErrorMessage(getIncompleteClassContributorArgsMessage(commandLineArgs));
					} else {
						parsedResult.setResultString(classNames);
					}
				} else {
					// do nothing yet
				}
			} else {
				parsedResult.setErrorMessage(getInvalidArgsPassedMessage(commandLineArgs));
			}
		}
	}
		
	public static String getNoArgumentsPassedMessage() {
		return "No arguments passed.";
	}

	private boolean argumentsAreValid(String commandLineArgs) {
		commandLineArgs = commandLineArgs.trim();
		return commandLineArgs.toLowerCase().startsWith(CLASS_OWNER_SWITCH);
	}

	public String getCommandLineArguments() {
		return commandLineArgs;
	}
	
	public ArgumentsParsedResult getParseResults() {
		return parsedResult;
	}

	public static String getInvalidArgsPassedMessage(String inCommandLineArgs) {
		return "Invalid arguments passed: [" + inCommandLineArgs + "]. Please see usage text.\n\n";
	}
	
	public static String getIncompleteClassContributorArgsMessage(String inCommandLineArgs) {
		return "Incomplete Class Owner's arguments were passed: [" + inCommandLineArgs + "]. Class name(s) missing. Please see usage text.\n\n";
	}
}