<!DOCTYPE html>

<#import "lib/utils.ftl" as u>

<@u.page>

    <#function homeOrAway value>
      <#if value.name() == "home">
        <#return "(H)"/>
      <#elseif value.name() == "away">
        <#return "(A)"/>
      <#else>
        <#return "(N)"/>
      </#if>
    </#function>
    <center>
        <table style="border-collapse: collapse; table-layout: fixed; font-family: monospace;" width=100%>
            <tr>
                <td align="center"></td>
                <#list leaguePositions as pos>
                    <td align="center"><img src="badges/${pos.name}.svg" alt="${pos.name}"></td>
                </#list>
            </tr>
            <tr>
                <td align="center">Played</td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: white">${pos.played}</td>
                </#list>
            </tr>
            <tr>
                <td align="center">Points</td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: white">${pos.points}</td>
                </#list>
            </tr>
            <tr>
                <td align="center">Max Points</td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: white">${pos.maxPoints}</td>
                </#list>
            </tr>
            <tr>
                <td align="center">Can Win League?</td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: white">${pos.canWinLeague}</td>
                </#list>
            </tr>
        </table>

        <br>
        <table style="border-collapse: collapse; table-layout: fixed; font-family: monospace;" width=100%>
            <#list fixtures as date,fixtures>
                <tr>
                    <td align="center">${date.shortDate}</td>
                    <#list fixtures as fixture>
                        <#if fixture??>
                            <#if fixture.competition == "Premier League">
                                <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: white"><font size="3">${fixture.opponent} ${homeOrAway(fixture.homeOrAway)}</font></td>
                            <#else>
                                <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: white"><font size="3" color="#cccccc">${fixture.opponent} ${homeOrAway(fixture.homeOrAway)}</font></td>
                            </#if>
                        <#else>
                            <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: white"></td>
                        </#if>
                    </#list>
                </tr>
            </#list>
        </table>

    </center>
</@u.page>