package z.eventbus

import groovy.json.JsonSlurper

class IntegrationEvent {
    private final String json
    private final Map root

    IntegrationEvent(String json) {
        this.json = json
        this.root = new JsonSlurper().parseText(json)
    }

    String type() {
        getClass().canonicalName
    }

    String toString() {
        this.json
    }
}
