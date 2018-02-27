<!DOCTYPE html>

<#import "lib/utils.ftl" as u>

<@u.page>
    <center>
        <table style="border-collapse: collapse; table-layout: fixed; font-family: monospace;" width=100%>
            <tr>
                <td align="center"></td>
                <#list leaguePositions as pos>
                    <td align="center"><img src="badges/${pos.name}.png" alt="${pos.name}"></td>
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
                    <td align="center">${date}</td>
                    <#list fixtures as opponent>
                        <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: white"><font size="3">${opponent}</font></td>
                    </#list>
                </tr>
            </#list>
        </table>

    </center>
</@u.page>