package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> entriesMap = new HashMap();

    private long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        Long id = currentId++;
        TimeEntry newTimeEntry = new TimeEntry(
                id,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        entriesMap.put(id, newTimeEntry);
        return newTimeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return entriesMap.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return (List<TimeEntry>) new ArrayList<>(entriesMap.values());
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (entriesMap.get(id) != null) {
            TimeEntry updatedEntry = new TimeEntry(
                    id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours()
            );

            entriesMap.replace(id, updatedEntry);
            return updatedEntry;
        }
        return null;
//
//        entriesMap.replace(id, timeEntry);
//        return timeEntry;
    }

    @Override
    public void delete(long id) {
        entriesMap.remove(id);
    }
}
