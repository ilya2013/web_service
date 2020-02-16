package ru.auroramusic.race.data;

public class ResultRecord implements Comparable{
    private Result result;
    private boolean showed;

    public ResultRecord(Result result) {
        this.result = result;
    }

    public boolean isShowed() {
        return showed;
    }

    @Override
    public int compareTo(Object o) {
        ResultRecord record2 = (ResultRecord) o;
        String raceId1 = this.result.getRaceId() == null ? "z" : this.result.getRaceId();
        String raceId2 = record2.result.getRaceId() == null ? "z" : record2.result.getRaceId();
//        String dtFinish = this.result.getDtFinish() == null ? "z" : this.result.getDtFinish();
        int startOrder = this.result.getStartOrder();
        int startOrder2 = record2.result.getStartOrder();
        String dtFinish2 = record2.result.getDtFinish() == null ? "z" : record2.result.getDtFinish();
        return raceId1.equals(raceId2) ?
                Integer.compare(startOrder, startOrder2)
                : raceId1.compareTo(raceId2);
    }
    //TODO Добавить проверки типов, написать тесты
    @Override
    public boolean equals(Object obj) {
        ResultRecord record = (ResultRecord) obj;
        return this.result.getRaceId().equals(record.result.getRaceId());
    }

    @Override
    public String toString() {
        return "ResultRecord{" +
                "result=" + result +
                ", showed=" + showed +
                '}';
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public void setShowed(boolean showed) {
        this.showed = showed;
    }
}
