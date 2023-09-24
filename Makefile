.PHONY: up
up:
	@docker-compose up -d

.PHONY: stop
stop:
	@docker compose stop

.PHONY: build
build:
	@gradlew build --warning-mode all



.PHONY: run-tests
run-test:
	@gradlew test --warning-mode all

.PHONY: test
test:
	@docker-compose up -d mysql-test
	@gradlew :test

.PHONY: run
run:
	@docker-compose up -d mysql
	@gradlew :bootRun


