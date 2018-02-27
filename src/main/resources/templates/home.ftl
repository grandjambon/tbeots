<!DOCTYPE html>

<#import "lib/utils.ftl" as u>

<@u.page>
    <center>
        <table style="border-collapse: collapse; table-layout: fixed; font-family: monospace" width=100%>
            <tr >
                <td align="center"><font color="#d9d9d9">Position</font></td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: ${pos.backgroundColor}; color: ${pos.foregroundColor}">${pos.position}</td>
                </#list>
            </tr>
            <tr>
                <td align="center"><font color="#d9d9d9">Team</font></td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: ${pos.backgroundColor}; color: ${pos.foregroundColor}">${pos.name}</td>
                </#list>
            </tr>
            <tr>
                <td align="center"><font color="#d9d9d9">Played</font></td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: ${pos.backgroundColor}; color: ${pos.foregroundColor}">${pos.played}</td>
                </#list>
            </tr>
            <tr>
                <td align="center"><font color="#d9d9d9">Points</font></td>
                <#list leaguePositions as pos>
                    <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse; background-color: ${pos.backgroundColor}; color: ${pos.foregroundColor}">${pos.points}</td>
                </#list>
            </tr>
        </table>

        <br>
        <table style="border-collapse: collapse; table-layout: fixed; font-family: monospace" width=100%>
            <#list fixtures as date,fixtures>
                <tr>
                    <td align="center">${date}</td>
                    <#list fixtures as opponent>
                        <td align="center" style="border: 1px solid #ccccff; border-collapse:collapse;"><font size="3">${opponent}</font></td>
                    </#list>
                </tr>
            </#list>
        </table>

    </center>
</@u.page>