<!DOCTYPE html>

<#import "lib/utils.ftl" as u>

<@u.page>
    <center>
        <table style="border-collapse: collapse; table-layout: fixed" width=100%>
            <tr >
                <td align="center"><font color="#d9d9d9">Position</font></td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse;">${pos.position}</td>
                </#list>
            </tr>
            <tr>
                <td align="center"><font color="#d9d9d9">Team</font></td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse;">${pos.name}</td>
                </#list>
            </tr>
            <tr>
                <td align="center"><font color="#d9d9d9">Played</font></td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse;">${pos.played}</td>
                </#list>
            </tr>
            <tr>
                <td align="center"><font color="#d9d9d9">Points</font></td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse;">${pos.points}</td>
                </#list>
            </tr>
        </table>

        <br>
        <table style="border-collapse: collapse; table-layout: fixed" width=100%>
            <#list fixtures as date,fixtures>
                <tr>
                    <td align="center">${date}</td>
                    <#list fixtures as opponent>
                        <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse;"><font size="2">${opponent}</font></td>
                    </#list>
                </tr>
            </#list>
        </table>

    </center>
</@u.page>