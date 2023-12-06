package com.webflux.pokedex.model;

import java.util.Objects;

public class PokemonEvent {

        public PokemonEvent(long eventId, String eventType) {
                this.eventId = eventId;
                this.eventType = eventType;
        }

        public long getEventId() {
                return eventId;
        }

        public void setEventId(long eventId) {
                this.eventId = eventId;
        }

        public String getEventType() {
                return eventType;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                PokemonEvent that = (PokemonEvent) o;
                return eventId == that.eventId && Objects.equals(eventType, that.eventType);
        }

        @Override
        public int hashCode() {
                return Objects.hash(eventId, eventType);
        }

        public void setEventType(String eventType) {
                this.eventType = eventType;
        }

        private long eventId;

        private String eventType;

        @Override
        public String toString() {
                return "PokemonEvent{" +
                        "eventId=" + eventId +
                        ", eventType='" + eventType + '\'' +
                        '}';
        }

        public PokemonEvent() {
                super();
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
                return super.clone();
        }
}
