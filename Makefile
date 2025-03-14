GRADLE := ./gradlew

fmt:
	$(GRADLE) spotlessApply

test:
	$(GRADLE) test

run:
	$(GRADLE) run
