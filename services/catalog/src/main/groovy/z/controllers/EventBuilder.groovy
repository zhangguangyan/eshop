package z.controllers

import groovy.json.JsonOutput

class EventBuilder {
    static String productAdded(CatalogItem1 item) {
        JsonOutput.toJson([
            name: item.name
        ])
    }
}
