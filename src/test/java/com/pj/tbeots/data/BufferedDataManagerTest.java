package com.pj.tbeots.data;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
public class BufferedDataManagerTest {
    private static final LocalDate LAST_SUNDAY = LocalDate.of(2021, 1, 31);

    private static final LocalDate MONDAY = LocalDate.of(2021, 2, 1);
    private static final LocalDate SATURDAY = LocalDate.of(2021, 2, 6);
    private static final LocalDate SUNDAY = LocalDate.of(2021, 2, 7);

    @Mock private DataManager mockDataManager;

    private static Stream<Arguments> inputValues() {
        return Stream.of(
            arguments(false, ldt(LAST_SUNDAY, 22,0), ldt(MONDAY, 14, 0)),// not within window for Monday
            arguments(true, null, ldt(MONDAY, 14, 0)),       // not initialised so initialise regardless of it being outside of the window
            arguments(true, ldt(LAST_SUNDAY, 22,0), ldt(MONDAY, 18, 10)), // within window for Monday, and longer than 15 mins
            arguments(false, ldt(MONDAY, 21, 45), ldt(MONDAY, 23, 5)),    // after window has closed for Monday
            arguments(true, ldt(SATURDAY, 14,0), ldt(SATURDAY, 14, 20)), // within window for Saturday, and longer than 15 mins since last refresh
            arguments(false, ldt(SUNDAY, 14,0), ldt(SUNDAY, 14,10))  // within window for Sunday, but not 15 mins since last refresh
        );
    }

    @ParameterizedTest
    @MethodSource("inputValues")
    public void verifyShouldRefresh(boolean shouldRefresh, LocalDateTime lastRefresh, LocalDateTime now) throws IOException {
        BufferedDataManager bufferedDataManager = new BufferedDataManager(mockDataManager, lastRefresh);
        bufferedDataManager.refreshCache(now);
        if (shouldRefresh) {
            assertRefresh();
        }
        verifyNoMoreInteractions(mockDataManager);
    }

    private void assertRefresh() throws IOException {
        verify(mockDataManager).getTeams();
        verify(mockDataManager).getLeaguePositions();
        verify(mockDataManager).getFixtures();
    }

    private static LocalDateTime ldt(LocalDate day, int hour, int min) {
        return LocalDateTime.of(day, LocalTime.of(hour,min));
    }
}