import java.util.*;
import java.util.stream.Collectors;

public class LeagueTable {
    private final List<Match> matches;

    public LeagueTable(final List<Match> matches) {
        this.matches = matches;
    }

    /**
     * Get the ordered list of league table entries for this league table.
     *
     * @return
     */
    //Note-> Points are calculated based assumption for each Win 2 point, Draw 1-1 points to both team and loss 0 points
    public List<LeagueTableEntry> getTableEntries() {
        System.out.println("About to calculate LeagueTable for total matches: "+ matches.size());
        List<LeagueTableEntry> entries = new ArrayList<>();
        if (Objects.isNull(matches)) {
            return Collections.EMPTY_LIST;
        }

        Map<String, List<Match>> matchPlayedInHome = matches.stream().collect(Collectors.groupingBy(Match::getHomeTeam));
        Map<String, List<Match>> matchPlayedAway = matches.stream().collect(Collectors.groupingBy(Match::getAwayTeam));

        Map<String, List<Match>> totalMatchesPerTeam = new HashMap<>();

        Set<String> uniqueTeams = new HashSet<>(matchPlayedInHome.keySet());
        uniqueTeams.addAll(matchPlayedAway.keySet());

        uniqueTeams.stream().forEach(t ->{
            List<Match> matches = new ArrayList<>(matchPlayedInHome.get(t));
            matches.addAll(matchPlayedAway.get(t));
            totalMatchesPerTeam.put(t, matches);
        });


        totalMatchesPerTeam.entrySet().stream().forEach(e -> {
            LeagueTableEntry entry = buildTableEntry(e.getKey(), e.getValue());
            entries.add(entry);
        });

        //sort before returning based on sorted by points, goal difference, goals for and then team names
        Comparator<LeagueTableEntry> comparator = Comparator.comparing( LeagueTableEntry::getPoints )
                .thenComparing(LeagueTableEntry::getGoalDifference)
                .thenComparing(LeagueTableEntry::getGoalsFor)
                .thenComparing(LeagueTableEntry::getTeamName);

        return entries.stream().sorted(comparator.reversed()).collect(Collectors.toList());

    }

    private LeagueTableEntry buildTableEntry(String teamName, List<Match> matches) {
        final int matchedPlayed = matches.size();
        int won = 0, loss = 0, draw = 0,  goalsFor = 0, goalAgainst = 0;
        for(Match m : matches) {
            if (m.getHomeScore() == m.getAwayScore()) {
                draw = draw + 1;
                goalsFor = goalsFor + m.getHomeScore();
                goalAgainst = goalAgainst + m.getAwayScore();
            } else if (m.getHomeTeam().equals(teamName)) {
                if(m.getHomeScore() > m.getAwayScore()) {
                    won = won + 1;
                } else {
                    loss = loss + 1;
                }
                goalsFor = goalsFor + m.getHomeScore();
                goalAgainst = goalAgainst + m.getAwayScore();

            } else if(m.getAwayTeam().equals(teamName)){
                if(m.getHomeScore() < m.getAwayScore()){
                    won = won + 1;
                }
                else {
                    loss = loss + 1;
                }
                goalsFor = goalsFor + m.getAwayScore();
                goalAgainst = goalAgainst + m.getHomeScore();

            }
        }
        int goalDifference = goalsFor - goalAgainst;

        //Note-> Points are calculated based assumption for each Win 2 point, Draw 1-1 points to both team and loss 0 points
        int points = won * 2  + draw * 1 + loss * 0;

        return new LeagueTableEntry(teamName, matchedPlayed, won, draw, loss, goalsFor, goalAgainst, goalDifference, points);
    }
}

