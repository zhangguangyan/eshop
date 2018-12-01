package z.controllers

import groovy.json.JsonOutput

class EventBuilder {
    static String productAdded(String name) {
        JsonOutput.toJson([
            name: name
        ])
    }
}
