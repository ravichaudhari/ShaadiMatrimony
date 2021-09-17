package com.shaadi.matrimony.holder;

import java.util.List;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class UserList {

    private List<Result> results = null;
    private Info info;

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }
}
