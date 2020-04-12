package com.pluralsight.tddjunit5.airport;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;

public class AirportTest {

	@DisplayName("Given there is an economy flight")
	@Nested
	class EconomyFlightTest {

		private Flight economyFlight;
		private Passenger mike;
		private Passenger john;

		@BeforeEach
		void setUp() {
			economyFlight = new EconomyFlight("1");
			mike = new Passenger("Mike", false);
			john = new Passenger("John", true);
		}

		@Nested
		@DisplayName("When we have a usual passenger")
		class UsualPassenger {

			@Test
			@DisplayName("then you can add and remove him from economy flight")
			public void testEconomyFlightUsualPassenger() {

				Assertions.assertAll("verify all conditions for a usual passenger and an economy flight",
						() -> assertEquals("1", economyFlight.getId()),
						() -> assertEquals(true, economyFlight.addPassenger(mike)),
						() -> assertEquals(1, economyFlight.getPassengersSet().size()),
						() -> equals(economyFlight.getPassengersSet().equals(mike)),
						() -> assertEquals(true, economyFlight.removePassenger(mike)),
						() -> assertEquals(0, economyFlight.getPassengersSet().size()));
			}

			@DisplayName("Then you cannot add him to an economy flight more than once")
			@RepeatedTest(5)
//			@Disabled
			public void testEconomyFlightUsualPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
				for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
					economyFlight.addPassenger(mike);
				}

				Assertions.assertAll("Verify a usual passenger can be added to an economy flight only once",
						() -> assertEquals(1, economyFlight.getPassengersSet().size()),
						() -> equals(economyFlight.getPassengersSet().contains(mike)), // assertTrue
						() -> equals(
								new ArrayList<>(economyFlight.getPassengersSet()).get(0).getName().equals("Mike"))); // assertTrue
			}
		}

		@Nested
		@DisplayName("when we have a VIP passenger")
		class VipPassenger {

			@Test
			@DisplayName("then you can add him but cannot remove him from economy flight")
			public void testEconomyFlightVipPassenger() {

				Assertions.assertAll("verify all conditions for a vip passenger and an economy flight",
						() -> assertEquals("1", economyFlight.getId()),
						() -> assertEquals(true, economyFlight.addPassenger(john)),
						() -> assertEquals(1, economyFlight.getPassengersSet().size()),
						() -> equals(economyFlight.getPassengersSet().contains(john)),
						() -> assertEquals(false, economyFlight.removePassenger(john)),
						() -> assertEquals(1, economyFlight.getPassengersSet().size()));
			}
		}
	}

	@DisplayName("Given there is an business flight")
	@Nested
	public class BusinessFligthTest {

		private Flight businessFlight;
		private Passenger mike;
		private Passenger john;

		@BeforeEach
		void setUp() {
			businessFlight = new BusinessFlight("2");
			mike = new Passenger("Mike", false);
			john = new Passenger("John", true);
		}

		@Nested
		@DisplayName("When we have a usual passenger")
		class UsualPassenger {

			@Test
			@DisplayName("then you can add and remove him from business flight")
			public void testBusinessFlightUsualPassenger() {

				Assertions.assertAll("verify all conditions for a usual passenger and a business flight",
						() -> assertEquals(false, businessFlight.addPassenger(mike)),
						() -> assertEquals(0, businessFlight.getPassengersSet().size()),
						() -> assertEquals(false, businessFlight.removePassenger(mike)),
						() -> assertEquals(0, businessFlight.getPassengersSet().size()));
			}

			@Nested
			@DisplayName("When we have a vip passenger")
			class VipPassenger {

				@Test
				@DisplayName("then you can add and remove him from business flight")
				public void testBusinessFlightVipPassenger() {

					Assertions.assertAll("Verify all conditions for vip passenger and a business flight",
							() -> assertEquals(true, businessFlight.addPassenger(john)),
							() -> assertEquals(1, businessFlight.getPassengersSet().size()),
							() -> assertEquals(false, businessFlight.removePassenger(john)),
							() -> assertEquals(1, businessFlight.getPassengersSet().size()));
				}

				@DisplayName("The you cannot add him to a business flight more than once")
				@RepeatedTest(5)
				public void testBusinessFlightVipPassengerAddedOnlyOnde(RepetitionInfo repetitionInfo) {
					for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
						businessFlight.addPassenger(john);
					}

					Assertions.assertAll("Verify a VIP passenger can be added to a business flight only once",
							() -> assertEquals(1, businessFlight.getPassengersSet().size()),
							() -> equals(businessFlight.getPassengersSet().contains(john)),
							() -> equals(new ArrayList<>(businessFlight.getPassengersSet()).get(0).getName()
									.equals("john")));
				}
			}
		}

		@DisplayName("Given there is a premium flight")
		@Nested
		class PremiumFlightTest {
			private Flight premiumFlight;
			private Passenger mike;
			private Passenger john;

			@BeforeEach
			void setUp() {
				premiumFlight = new PremiumFlight("3");
				mike = new Passenger("Mike", false);
				john = new Passenger("John", true);
			}

			@Nested
			@DisplayName("When we have a usual paassenger")
			class UsualPassenger {

				@Test
				@DisplayName("Then you cannot add or remove him from a premium flight")
				public void testPremiumFlightUsualPassenger() {
					Assertions.assertAll("Verify all condition for a usual passenger and a premium flight",
							() -> assertEquals(false, premiumFlight.addPassenger(mike)),
							() -> assertEquals(0, premiumFlight.getPassengersSet().size()),
							() -> assertEquals(false, premiumFlight.removePassenger(mike)),
							() -> assertEquals(0, premiumFlight.getPassengersSet().size()));
				}
			}

			@Nested
			@DisplayName("When we have a VIP passenger")
			class VipPassenger {

				@Test
				@DisplayName("Then you can add and remove him from a premium flight")

				public void testPremiumFlightVipPassenger() {
					Assertions.assertAll("Verify all conditions for a VIP passenger and a premium flight",
							() -> assertEquals(true, premiumFlight.addPassenger(john)),
							() -> assertEquals(1, premiumFlight.getPassengersSet().size()),
							() -> assertEquals(true, premiumFlight.removePassenger(john)),
							() -> assertEquals(0, premiumFlight.getPassengersSet().size()));
				}

				@DisplayName("Then you cannot add him to a premium flight more than once")
				@RepeatedTest(5)
				public void testPremiumFlightVipPassengerAddedOnlyOnce(RepetitionInfo repetitionInfo) {
					for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
						premiumFlight.addPassenger(john);
					}

					Assertions.assertAll("Verify a VIP passenger can be added to a premium flight only once",
							() -> assertEquals(1, premiumFlight.getPassengersSet().size()),
							() -> equals(premiumFlight.getPassengersSet().contains(john)), () -> equals(
									new ArrayList<>(premiumFlight.getPassengersSet()).get(0).getName().equals("John")));
				}
			}
		}
	}
}
