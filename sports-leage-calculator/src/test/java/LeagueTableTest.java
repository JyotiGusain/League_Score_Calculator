import java.util.ArrayList;
import java.util.List;

public class LeagueTableTest {
    //would like to use junit and assertion, but now will test with main method

    public static void main(String[] args) {
        LeagueTableTest tableTest = new LeagueTableTest();

        //test 1
        tableTest.shouldReturnEmptyTableWhenThereIsNoCompletestMatches();

        //test case 2

        tableTest.shouldGetTableEntries();


    }

    private void shouldReturnEmptyTableWhenThereIsNoCompletestMatches() {
        LeagueTable leagueTable = new LeagueTable(null);
        List<LeagueTableEntry> actualEntries = leagueTable.getTableEntries();
        System.out.println("Test case 1 result");
        System.out.println(actualEntries.isEmpty());
    }

    public  void shouldGetTableEntries()
    {
        System.out.println("Test case 2 result");
        //given
        final List<Match> matches = populateMatchesData();
        final List<LeagueTableEntry> expectedEntries = expectedLeagueTableEntry();


        //when
        LeagueTable leagueTable = new LeagueTable(matches);
        List<LeagueTableEntry> actualEntries = leagueTable.getTableEntries();

        //then

        System.out.println("Top team name should be CSK: "+ actualEntries.get(0).getTeamName().equals(TeamName.CSK.name()));
        System.out.println("2nd Top team name should be MI: "+ actualEntries.get(1).getTeamName().equals(TeamName.MI.name()));
        System.out.println("3rd Top team name should be DD: "+ actualEntries.get(2).getTeamName().equals(TeamName.DD.name()));
        System.out.println("4th Top team name should be RCB: "+ actualEntries.get(3).getTeamName().equals(TeamName.RCB.name()));

        assertValues(actualEntries.get(0), expectedEntries.get(0));
        assertValues(actualEntries.get(1), expectedEntries.get(1));
        assertValues(actualEntries.get(2), expectedEntries.get(2));
        assertValues(actualEntries.get(3), expectedEntries.get(3));
    }

    private void assertValues(LeagueTableEntry actualEntry, LeagueTableEntry expectedEntry){
        System.out.println("Actual and expected comparision for team: "+ actualEntry.getTeamName());
        System.out.println(actualEntry.getTeamName().equals(expectedEntry.getTeamName()));
        System.out.println(actualEntry.getPoints() == (expectedEntry.getPoints()));
        System.out.println(actualEntry.getGoalDifference() == (expectedEntry.getGoalDifference()));
        System.out.println(actualEntry.getGoalsFor() == (expectedEntry.getGoalsFor()));
        System.out.println(actualEntry.getGoalsAgainst() == (expectedEntry.getGoalsAgainst()));
        System.out.println(actualEntry.getWon() == (expectedEntry.getWon()));
        System.out.println(actualEntry.getLost()== (expectedEntry.getLost()));
        System.out.println(actualEntry.getDrawn() == (expectedEntry.getDrawn()));
        System.out.println(actualEntry.getPlayed() == (expectedEntry.getPlayed()));
    }

    private List<Match> populateMatchesData()
    {
        List<Match> matches= new ArrayList<>();
        Match match1 = new Match(TeamName.RCB.name(), TeamName.CSK.name(), 2, 3);
        Match match2= new Match(TeamName.CSK.name(),TeamName.RCB.name(),5,5);
        Match match3= new Match(TeamName.RCB.name(),TeamName.MI.name(),4,0);
        Match match4= new Match(TeamName.MI.name(),TeamName.RCB.name(),7,6);
        Match match5= new Match(TeamName.RCB.name(),TeamName.DD.name(),10,20);
        Match match6= new Match(TeamName.DD.name(),TeamName.RCB.name(),17,19);
        Match match7= new Match(TeamName.CSK.name(),TeamName.DD.name(),4,2);
        Match match8= new Match(TeamName.DD.name(),TeamName.CSK.name(),7,5);
        Match match9= new Match(TeamName.CSK.name(),TeamName.MI.name(),17,19);
        Match match10= new Match(TeamName.MI.name(),TeamName.CSK.name(),3,9);
        Match match11= new Match(TeamName.MI.name(),TeamName.DD.name(),4,4);
        Match match12= new Match(TeamName.DD.name(),TeamName.MI.name(),19,22);

        matches.add(match1);
        matches.add(match2);
        matches.add(match3);
        matches.add(match4);
        matches.add(match5);
        matches.add(match6);
        matches.add(match7);
        matches.add(match8);
        matches.add(match9);
        matches.add(match10);
        matches.add(match11);
        matches.add(match12);

        return matches;

    }

    private List<LeagueTableEntry> expectedLeagueTableEntry()
    {
        List<LeagueTableEntry> leagueTableEntries= new ArrayList<>();
        LeagueTableEntry cskTable = new LeagueTableEntry(TeamName.CSK.name(),6,3,1,2,43,38,5,7);
        LeagueTableEntry rcbTable = new LeagueTableEntry(TeamName.RCB.name(),6,2,1,3,46,52,-6,5);
        LeagueTableEntry delhiTable = new LeagueTableEntry(TeamName.DD.name(),6,2,1,3,69,64,5,5);
        LeagueTableEntry miTable = new LeagueTableEntry(TeamName.MI.name(),6,3,1,2,55,59,-4,7);

        leagueTableEntries.add(cskTable);
        leagueTableEntries.add(miTable);
        leagueTableEntries.add(delhiTable);
        leagueTableEntries.add(rcbTable);

        return leagueTableEntries;
    }
}

enum TeamName{
    CSK("Chennai Super King"),
    MI("Mumbai Indian"),
    DD("Dehli Daredevils"),
    RCB("Royal Challenger Bangalore");

    private String name;
    TeamName(String name){
        this.name = name;
    }
}