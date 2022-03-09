import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class Unjumbler {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("usa.txt");
		Scanner scan = new Scanner(file);
		ArrayList<String> dictionary = new ArrayList<String>();
		while (scan.hasNextLine()) {
			dictionary.add(scan.nextLine());
		}
		System.out.println("Enter scrambled word");
		Scanner scan2 = new Scanner(System.in);
		String input = scan2.nextLine().toLowerCase();
		ArrayList<String> answers = new ArrayList<String>();
		ArrayList<String> options = new ArrayList<String>();

		switch (input.length()) {
			case 2:
				options = two(input);
				break;
			case 3:
				options = three(input);
				break;
			case 4:
				options = four(input);
				break;
			case 5:
				options = five(input);
				break;
			case 6:
				options = six(input);
				break;
			case 7:
				options = seven(input);
				break;
			case 8:
				options = eight(input);
				break;
			case 9:
				options = nine(input);
				break;
			case 10:
				options = ten(input);
				break;
		}

		for (int i = 0; i < dictionary.size(); i++) {
			if (dictionary.get(i).length() != input.length()) {
				dictionary.remove(i);
				i--;
			}
		}

		System.out.println(dictionary.size());

		for (String option : options) {
			for (String word : dictionary) {
				if (option.contentEquals(word) && !answers.contains(word)) {
					answers.add(word);
				}
			}
		}

		for (String answer : answers) {
			System.out.println(answer);
		}
		scan.close();
		scan2.close();
	}

	// I need to figure out how to arrive at every single combination of letters
	//
	public static ArrayList<String> two(String input) {
		ArrayList<String> options = new ArrayList<String>();
		options.add(input);
		char[] letters = input.toCharArray();
		char temp = letters[0];
		letters[0] = letters[1];
		letters[1] = temp;
		System.out.println();
		input = new String(letters);
		options.add(input);
		return options;
	}

	public static ArrayList<String> three(String input) {

		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 3; i++) {
			ArrayList<Character> answer = new ArrayList<Character>();
			ArrayList<Character> letterBank = new ArrayList<Character>();

			// refresh letterBank
			for (char letter : input.toCharArray()) {
				letterBank.add(letter);
			}
			// Stores the ordinal index of each letter in the letterBank, holds as many
			// letters as the letterBank size
			Map<Character, Integer> currentAppearance = new HashMap<>();

			for (Character letter : letterBank) {
				currentAppearance.put(letter, 1);
			}

			// Put letter from letterBank to answer
			answer.add(letterBank.get(i));
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			letterBank.remove(i);

			for (int j = 0; j < 2; j++) {

				// Make sure there is only one letter at this stage
				if (answer.size() == 2) {
					letterBank.add(answer.get(1));
					currentAppearance.put(answer.get(1), currentAppearance.get(answer.get(1)) - 1);
					answer.remove(1);
				}

				sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

				// Put letter from letterBank to answer
				answer.add(letterBank.get(j));
				currentAppearance.put(letterBank.get(j), currentAppearance.get(letterBank.get(j)) + 1);
				letterBank.remove(j);

				for (int k = 0; k < 1; k++) {

					sortLetterBank(input, letterBank, currentAppearance);

					// Add the last letter
					answer.add(letterBank.get(k));
					letterBank.remove(k);

					// Make the string and add it to the answers
					StringBuilder builder = new StringBuilder();
					for (Character c : answer) {
						builder.append(c);
					}
					options.add(builder.toString());

					// Remove the letter you just added, so that there are only 2 letters, and add
					// it to the letterbank
					letterBank.add(answer.get(answer.size() - 1));
					answer.remove(answer.size() - 1);

				}
			}
		}
		return options;
	}

	public static ArrayList<String> four(String input) {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 4; i++) {
			ArrayList<Character> answer = new ArrayList<Character>();
			ArrayList<Character> letterBank = new ArrayList<Character>();

			// refresh letterBank
			for (char letter : input.toCharArray()) {
				letterBank.add(letter);
			}

			Map<Character, Integer> currentAppearance = new HashMap<>();

			for (Character letter : letterBank) {
				currentAppearance.put(letter, 1);
			}

			// Put letter from letterBank to answer
			answer.add(letterBank.get(i));
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			letterBank.remove(i);

			// When you remove a letter from letterBank you increment the value in
			// currentAppearance

			for (int j = 0; j < 3; j++) {
				// Make sure there is only one letter at this stage

				if (letterBank.size() < 3) {

					// Reset the letterBank to have the same number of letters as the loop length
					for (int z = answer.size() - 1; letterBank.size() < 3; z--) {

						// When you add a letter to the LetterBank you decrement the value in
						// CurrentAppearance
						letterBank.add(answer.get(z));
						currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
						answer.remove(z);

					}

				}

				sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

				// Put letter from letterBank to answer
				answer.add(letterBank.get(j));
				currentAppearance.put(letterBank.get(j), currentAppearance.get(letterBank.get(j)) + 1);
				letterBank.remove(j);

				for (int l = 0; l < 2; l++) {
					if (answer.size() == 3) {
						letterBank.add(answer.get(2));
						currentAppearance.put(answer.get(2), currentAppearance.get(answer.get(2)) - 1);
						answer.remove(2);
					}

					sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

					answer.add(letterBank.get(l));
					currentAppearance.put(letterBank.get(l), currentAppearance.get(letterBank.get(l)) + 1);
					letterBank.remove(l);

					for (int k = 0; k < 1; k++) {

						sortLetterBank(input, letterBank, currentAppearance);

						// Add the last letter
						answer.add(letterBank.get(k));
						letterBank.remove(k);

						// Make the string and add it to the answers
						StringBuilder builder = new StringBuilder();
						for (Character c : answer) {
							builder.append(c);
						}
						options.add(builder.toString());

						// Remove the letter you just added, so that there are only 2 letters, and add
						// it to the letterbank
						letterBank.add(answer.get(answer.size() - 1));
						answer.remove(answer.size() - 1);

					}
				}

			}
		}
		return options;
	}

	public static ArrayList<String> five(String input) {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 5; i++) {
			ArrayList<Character> answer = new ArrayList<Character>();
			ArrayList<Character> letterBank = new ArrayList<Character>();

			// refresh letterBank
			for (char letter : input.toCharArray()) {
				letterBank.add(letter);
			}

			Map<Character, Integer> currentAppearance = new HashMap<>();

			for (Character letter : letterBank) {
				currentAppearance.put(letter, 1);
			}

			// Put letter from letterBank to answer
			answer.add(letterBank.get(i));
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			letterBank.remove(i);

			for (int j = 0; j < 4; j++) {
				// Make sure there is only one letter at this stage
				if (letterBank.size() < 4) {

					for (int z = answer.size() - 1; letterBank.size() < 4; z--) {

						letterBank.add(answer.get(z));
						currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
						answer.remove(z);

					}

				}
				sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

				// Put letter from letterBank to answer
				answer.add(letterBank.get(j));
				currentAppearance.put(letterBank.get(j), currentAppearance.get(letterBank.get(j)) + 1);
				letterBank.remove(j);

				for (int l = 0; l < 3; l++) {
					if (letterBank.size() < 3) {

						for (int z = answer.size() - 1; letterBank.size() < 3; z--) {

							letterBank.add(answer.get(z));
							currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
							answer.remove(z);

						}

					}

					sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

					answer.add(letterBank.get(l));
					currentAppearance.put(letterBank.get(l), currentAppearance.get(letterBank.get(l)) + 1);
					letterBank.remove(l);

					for (int m = 0; m < 2; m++) {
						if (letterBank.size() < 2) {

							for (int z = answer.size() - 1; letterBank.size() < 2; z--) {

								letterBank.add(answer.get(z));
								currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
								answer.remove(z);

							}
						}
						sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

						answer.add(letterBank.get(m));
						currentAppearance.put(letterBank.get(m), currentAppearance.get(letterBank.get(m)) + 1);
						letterBank.remove(m);
						for (int k = 0; k < 1; k++) {

							sortLetterBank(input, letterBank, currentAppearance);

							// Add the last letter
							answer.add(letterBank.get(k));
							letterBank.remove(k);

							// Make the string and add it to the answers
							StringBuilder builder = new StringBuilder();
							for (Character c : answer) {
								builder.append(c);
							}
							options.add(builder.toString());

							// Remove the letter you just added, so that there are only 2 letters, and add
							// it to the letterbank
							letterBank.add(answer.get(answer.size() - 1));
							answer.remove(answer.size() - 1);

						}
					}
				}

			}
		}
		return options;
	}

	public static ArrayList<String> six(String input) {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 6; i++) {
			ArrayList<Character> answer = new ArrayList<Character>();
			ArrayList<Character> letterBank = new ArrayList<Character>();

			// refresh letterBank
			for (char letter : input.toCharArray()) {
				letterBank.add(letter);
			}

			Map<Character, Integer> currentAppearance = new HashMap<>();

			for (Character letter : letterBank) {
				currentAppearance.put(letter, 1);
			}

			// Put letter from letterBank to answer
			answer.add(letterBank.get(i));
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			letterBank.remove(i);

			for (int j = 0; j < 5; j++) {
				// Make sure there is only one letter at this stage
				if (letterBank.size() < 5) {

					for (int z = answer.size() - 1; letterBank.size() < 5; z--) {

						letterBank.add(answer.get(z));
						currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
						answer.remove(z);

					}

				}
				sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

				// Put letter from letterBank to answer
				answer.add(letterBank.get(j));
				currentAppearance.put(letterBank.get(j), currentAppearance.get(letterBank.get(j)) + 1);
				letterBank.remove(j);

				for (int l = 0; l < 4; l++) {

					if (letterBank.size() < 4) {

						for (int z = answer.size() - 1; letterBank.size() < 4; z--) {

							letterBank.add(answer.get(z));
							currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
							answer.remove(z);

						}

					}

					sortLetterBank(input, letterBank, currentAppearance);

					answer.add(letterBank.get(l));
					currentAppearance.put(letterBank.get(l), currentAppearance.get(letterBank.get(l)) + 1);
					letterBank.remove(l);

					for (int m = 0; m < 3; m++) {
						if (letterBank.size() < 3) {

							for (int z = answer.size() - 1; letterBank.size() < 3; z--) {

								letterBank.add(answer.get(z));
								currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
								answer.remove(z);

							}
						}

						sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

						answer.add(letterBank.get(m));
						currentAppearance.put(letterBank.get(m), currentAppearance.get(letterBank.get(m)) + 1);
						letterBank.remove(m);
						for (int n = 0; n < 2; n++) {
							if (letterBank.size() < 2) {

								for (int z = answer.size() - 1; letterBank.size() < 2; z--) {

									letterBank.add(answer.get(z));
									currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
									answer.remove(z);

								}
							}
							sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

							answer.add(letterBank.get(n));
							currentAppearance.put(letterBank.get(n), currentAppearance.get(letterBank.get(n)) + 1);
							letterBank.remove(n);
							for (int k = 0; k < 1; k++) {

								sortLetterBank(input, letterBank, currentAppearance);

								// Add the last letter
								answer.add(letterBank.get(k));
								letterBank.remove(k);

								// Make the string and add it to the answers
								StringBuilder builder = new StringBuilder();
								for (Character c : answer) {
									builder.append(c);
								}
								options.add(builder.toString());

								// Remove the letter you just added, so that there are only 2 letters, and add
								// it to the letterbank
								letterBank.add(answer.get(answer.size() - 1));
								answer.remove(answer.size() - 1);

							}
						}
					}
				}

			}
		}
		return options;
	}

	public static ArrayList<String> seven(String input) {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 7; i++) {
			ArrayList<Character> answer = new ArrayList<Character>();
			ArrayList<Character> letterBank = new ArrayList<Character>();

			// refresh letterBank
			for (char letter : input.toCharArray()) {
				letterBank.add(letter);
			}

			Map<Character, Integer> currentAppearance = new HashMap<>();

			for (Character letter : letterBank) {
				currentAppearance.put(letter, 1);
			}

			// Put letter from letterBank to answer
			answer.add(letterBank.get(i));
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			letterBank.remove(i);

			for (int j = 0; j < 6; j++) {
				// Make sure there is only one letter at this stage
				if (letterBank.size() < 6) {

					for (int z = answer.size() - 1; letterBank.size() < 6; z--) {

						letterBank.add(answer.get(z));
						currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
						answer.remove(z);

					}

				}
				sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

				// Put letter from letterBank to answer
				answer.add(letterBank.get(j));
				currentAppearance.put(letterBank.get(j), currentAppearance.get(letterBank.get(j)) + 1);
				letterBank.remove(j);

				for (int l = 0; l < 5; l++) {

					if (letterBank.size() < 5) {

						for (int z = answer.size() - 1; letterBank.size() < 5; z--) {

							letterBank.add(answer.get(z));
							currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
							answer.remove(z);

						}

					}

					sortLetterBank(input, letterBank, currentAppearance);

					answer.add(letterBank.get(l));
					currentAppearance.put(letterBank.get(l), currentAppearance.get(letterBank.get(l)) + 1);
					letterBank.remove(l);

					for (int m = 0; m < 4; m++) {
						if (letterBank.size() < 4) {

							for (int z = answer.size() - 1; letterBank.size() < 4; z--) {

								letterBank.add(answer.get(z));
								currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
								answer.remove(z);

							}
						}
						sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

						answer.add(letterBank.get(m));
						currentAppearance.put(letterBank.get(m), currentAppearance.get(letterBank.get(m)) + 1);
						letterBank.remove(m);

						for (int n = 0; n < 3; n++) {
							if (letterBank.size() < 3) {

								for (int z = answer.size() - 1; letterBank.size() < 3; z--) {

									letterBank.add(answer.get(z));
									currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
									answer.remove(z);

								}
							}
							sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

							answer.add(letterBank.get(n));
							currentAppearance.put(letterBank.get(n), currentAppearance.get(letterBank.get(n)) + 1);
							letterBank.remove(n);

							for (int g = 0; g < 2; g++) {

								if (letterBank.size() < 2) {

									for (int z = answer.size() - 1; letterBank.size() < 2; z--) {

										letterBank.add(answer.get(z));
										currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
										answer.remove(z);

									}
								}
								sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

								answer.add(letterBank.get(g));
								currentAppearance.put(letterBank.get(g), currentAppearance.get(letterBank.get(g)) + 1);
								letterBank.remove(g);

								for (int k = 0; k < 1; k++) {

									sortLetterBank(input, letterBank, currentAppearance);

									// Add the last letter
									answer.add(letterBank.get(k));
									letterBank.remove(k);

									// Make the string and add it to the answers
									StringBuilder builder = new StringBuilder();
									for (Character c : answer) {
										builder.append(c);
									}
									options.add(builder.toString());

									// Remove the letter you just added, so that there are only 2 letters, and add
									// it to the letterbank
									letterBank.add(answer.get(answer.size() - 1));
									answer.remove(answer.size() - 1);

								}
							}

						}
					}
				}

			}
		}
		return options;
	}

	public static ArrayList<String> eight(String input) {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 8; i++) {
			ArrayList<Character> answer = new ArrayList<Character>();
			ArrayList<Character> letterBank = new ArrayList<Character>();

			// refresh letterBank
			for (char letter : input.toCharArray()) {
				letterBank.add(letter);
			}

			Map<Character, Integer> currentAppearance = new HashMap<>();

			for (Character letter : letterBank) {
				currentAppearance.put(letter, 1);
			}

			// Put letter from letterBank to answer
			answer.add(letterBank.get(i));
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			letterBank.remove(i);

			for (int j = 0; j < 7; j++) {
				// Make sure there is only one letter at this stage
				if (letterBank.size() < 7) {

					for (int z = answer.size() - 1; letterBank.size() < 7; z--) {

						letterBank.add(answer.get(z));
						currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
						answer.remove(z);

					}

				}
				sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

				// Put letter from letterBank to answer
				answer.add(letterBank.get(j));
				currentAppearance.put(letterBank.get(j), currentAppearance.get(letterBank.get(j)) + 1);
				letterBank.remove(j);

				for (int l = 0; l < 6; l++) {

					if (letterBank.size() < 6) {

						for (int z = answer.size() - 1; letterBank.size() < 6; z--) {

							letterBank.add(answer.get(z));
							currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
							answer.remove(z);

						}

					}

					sortLetterBank(input, letterBank, currentAppearance);

					answer.add(letterBank.get(l));
					currentAppearance.put(letterBank.get(l), currentAppearance.get(letterBank.get(l)) + 1);
					letterBank.remove(l);

					for (int m = 0; m < 5; m++) {
						if (letterBank.size() < 5) {

							for (int z = answer.size() - 1; letterBank.size() < 5; z--) {

								letterBank.add(answer.get(z));
								currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
								answer.remove(z);

							}
						}
						sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

						answer.add(letterBank.get(m));
						currentAppearance.put(letterBank.get(m), currentAppearance.get(letterBank.get(m)) + 1);
						letterBank.remove(m);

						for (int n = 0; n < 4; n++) {
							if (letterBank.size() < 4) {

								for (int z = answer.size() - 1; letterBank.size() < 4; z--) {

									letterBank.add(answer.get(z));
									currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
									answer.remove(z);

								}
							}
							sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

							answer.add(letterBank.get(n));
							currentAppearance.put(letterBank.get(n), currentAppearance.get(letterBank.get(n)) + 1);
							letterBank.remove(n);

							for (int g = 0; g < 3; g++) {

								if (letterBank.size() < 3) {

									for (int z = answer.size() - 1; letterBank.size() < 3; z--) {

										letterBank.add(answer.get(z));
										currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
										answer.remove(z);

									}
								}
								sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

								answer.add(letterBank.get(g));
								currentAppearance.put(letterBank.get(g), currentAppearance.get(letterBank.get(g)) + 1);
								letterBank.remove(g);

								for (int h = 0; h < 2; h++) {

									if (letterBank.size() < 2) {

										for (int z = answer.size() - 1; letterBank.size() < 2; z--) {

											letterBank.add(answer.get(z));
											currentAppearance.put(answer.get(z),
													currentAppearance.get(answer.get(z)) - 1);
											answer.remove(z);

										}
									}
									sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

									answer.add(letterBank.get(h));
									currentAppearance.put(letterBank.get(h),
											currentAppearance.get(letterBank.get(h)) + 1);
									letterBank.remove(h);

									for (int k = 0; k < 1; k++) {

										sortLetterBank(input, letterBank, currentAppearance);

										// Add the last letter
										answer.add(letterBank.get(k));
										letterBank.remove(k);

										// Make the string and add it to the answers
										StringBuilder builder = new StringBuilder();
										for (Character c : answer) {
											builder.append(c);
										}
										options.add(builder.toString());

										// Remove the letter you just added, so that there are only 2 letters, and add
										// it to the letterbank
										letterBank.add(answer.get(answer.size() - 1));
										answer.remove(answer.size() - 1);

									}
								}
							}

						}
					}
				}

			}
		}
		return options;
	}

	public static ArrayList<String> nine(String input) {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 9; i++) {
			ArrayList<Character> answer = new ArrayList<Character>();
			ArrayList<Character> letterBank = new ArrayList<Character>();

			// refresh letterBank
			for (char letter : input.toCharArray()) {
				letterBank.add(letter);
			}

			Map<Character, Integer> currentAppearance = new HashMap<>();

			for (Character letter : letterBank) {
				currentAppearance.put(letter, 1);
			}

			// Put letter from letterBank to answer
			answer.add(letterBank.get(i));
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			letterBank.remove(i);

			for (int j = 0; j < 8; j++) {
				// Make sure there is only one letter at this stage
				if (letterBank.size() < 8) {

					for (int z = answer.size() - 1; letterBank.size() < 8; z--) {

						letterBank.add(answer.get(z));
						currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
						answer.remove(z);

					}

				}
				sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

				// Put letter from letterBank to answer
				answer.add(letterBank.get(j));
				currentAppearance.put(letterBank.get(j), currentAppearance.get(letterBank.get(j)) + 1);
				letterBank.remove(j);

				for (int l = 0; l < 7; l++) {

					if (letterBank.size() < 7) {

						for (int z = answer.size() - 1; letterBank.size() < 7; z--) {

							letterBank.add(answer.get(z));
							currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
							answer.remove(z);

						}

					}

					sortLetterBank(input, letterBank, currentAppearance);

					answer.add(letterBank.get(l));
					currentAppearance.put(letterBank.get(l), currentAppearance.get(letterBank.get(l)) + 1);
					letterBank.remove(l);

					for (int m = 0; m < 6; m++) {
						if (letterBank.size() < 6) {

							for (int z = answer.size() - 1; letterBank.size() < 6; z--) {

								letterBank.add(answer.get(z));
								currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
								answer.remove(z);

							}
						}
						sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

						answer.add(letterBank.get(m));
						currentAppearance.put(letterBank.get(m), currentAppearance.get(letterBank.get(m)) + 1);
						letterBank.remove(m);

						for (int n = 0; n < 5; n++) {
							if (letterBank.size() < 5) {

								for (int z = answer.size() - 1; letterBank.size() < 5; z--) {

									letterBank.add(answer.get(z));
									currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
									answer.remove(z);

								}
							}
							sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

							answer.add(letterBank.get(n));
							currentAppearance.put(letterBank.get(n), currentAppearance.get(letterBank.get(n)) + 1);
							letterBank.remove(n);

							for (int g = 0; g < 4; g++) {

								if (letterBank.size() < 4) {

									for (int z = answer.size() - 1; letterBank.size() < 4; z--) {

										letterBank.add(answer.get(z));
										currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
										answer.remove(z);

									}
								}
								sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

								answer.add(letterBank.get(g));
								currentAppearance.put(letterBank.get(g), currentAppearance.get(letterBank.get(g)) + 1);
								letterBank.remove(g);

								for (int h = 0; h < 3; h++) {

									if (letterBank.size() < 3) {

										for (int z = answer.size() - 1; letterBank.size() < 3; z--) {

											letterBank.add(answer.get(z));
											currentAppearance.put(answer.get(z),
													currentAppearance.get(answer.get(z)) - 1);
											answer.remove(z);

										}
									}
									sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

									answer.add(letterBank.get(h));
									currentAppearance.put(letterBank.get(h),
											currentAppearance.get(letterBank.get(h)) + 1);
									letterBank.remove(h);

									for (int f = 0; f < 2; f++) {

										if (letterBank.size() < 2) {

											for (int z = answer.size() - 1; letterBank.size() < 2; z--) {

												letterBank.add(answer.get(z));
												currentAppearance.put(answer.get(z),
														currentAppearance.get(answer.get(z)) - 1);
												answer.remove(z);

											}
										}
										sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

										answer.add(letterBank.get(f));
										currentAppearance.put(letterBank.get(f),
												currentAppearance.get(letterBank.get(f)) + 1);
										letterBank.remove(f);

										for (int k = 0; k < 1; k++) {

											sortLetterBank(input, letterBank, currentAppearance);

											// Add the last letter
											answer.add(letterBank.get(k));
											letterBank.remove(k);

											// Make the string and add it to the answers
											StringBuilder builder = new StringBuilder();
											for (Character c : answer) {
												builder.append(c);
											}
											options.add(builder.toString());

											// Remove the letter you just added, so that there are only 2 letters, and
											// add it to the letterbank
											letterBank.add(answer.get(answer.size() - 1));
											answer.remove(answer.size() - 1);

										}
									}
								}
							}

						}
					}
				}

			}
		}
		return options;
	}

	public static ArrayList<String> ten(String input) {
		ArrayList<String> options = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			ArrayList<Character> answer = new ArrayList<Character>();
			ArrayList<Character> letterBank = new ArrayList<Character>();

			// refresh letterBank
			for (char letter : input.toCharArray()) {
				letterBank.add(letter);
			}

			Map<Character, Integer> currentAppearance = new HashMap<>();

			for (Character letter : letterBank) {
				currentAppearance.put(letter, 1);
			}

			// Put letter from letterBank to answer
			answer.add(letterBank.get(i));
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			letterBank.remove(i);

			for (int j = 0; j < 9; j++) {
				// Make sure there is only one letter at this stage
				if (letterBank.size() < 9) {

					for (int z = answer.size() - 1; letterBank.size() < 9; z--) {

						letterBank.add(answer.get(z));
						currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
						answer.remove(z);

					}

				}
				sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

				// Put letter from letterBank to answer
				answer.add(letterBank.get(j));
				currentAppearance.put(letterBank.get(j), currentAppearance.get(letterBank.get(j)) + 1);
				letterBank.remove(j);

				for (int l = 0; l < 8; l++) {

					if (letterBank.size() < 8) {

						for (int z = answer.size() - 1; letterBank.size() < 8; z--) {

							letterBank.add(answer.get(z));
							currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
							answer.remove(z);

						}

					}

					sortLetterBank(input, letterBank, currentAppearance);

					answer.add(letterBank.get(l));
					currentAppearance.put(letterBank.get(l), currentAppearance.get(letterBank.get(l)) + 1);
					letterBank.remove(l);

					for (int m = 0; m < 7; m++) {
						if (letterBank.size() < 7) {

							for (int z = answer.size() - 1; letterBank.size() < 7; z--) {

								letterBank.add(answer.get(z));
								currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
								answer.remove(z);

							}
						}
						sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

						answer.add(letterBank.get(m));
						currentAppearance.put(letterBank.get(m), currentAppearance.get(letterBank.get(m)) + 1);
						letterBank.remove(m);

						for (int n = 0; n < 6; n++) {
							if (letterBank.size() < 6) {

								for (int z = answer.size() - 1; letterBank.size() < 6; z--) {

									letterBank.add(answer.get(z));
									currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
									answer.remove(z);

								}
							}
							sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

							answer.add(letterBank.get(n));
							currentAppearance.put(letterBank.get(n), currentAppearance.get(letterBank.get(n)) + 1);
							letterBank.remove(n);

							for (int g = 0; g < 5; g++) {

								if (letterBank.size() < 5) {

									for (int z = answer.size() - 1; letterBank.size() < 5; z--) {

										letterBank.add(answer.get(z));
										currentAppearance.put(answer.get(z), currentAppearance.get(answer.get(z)) - 1);
										answer.remove(z);

									}
								}
								sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

								answer.add(letterBank.get(g));
								currentAppearance.put(letterBank.get(g), currentAppearance.get(letterBank.get(g)) + 1);
								letterBank.remove(g);

								for (int h = 0; h < 4; h++) {

									if (letterBank.size() < 4) {

										for (int z = answer.size() - 1; letterBank.size() < 4; z--) {

											letterBank.add(answer.get(z));
											currentAppearance.put(answer.get(z),
													currentAppearance.get(answer.get(z)) - 1);
											answer.remove(z);

										}
									}
									sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

									answer.add(letterBank.get(h));
									currentAppearance.put(letterBank.get(h),
											currentAppearance.get(letterBank.get(h)) + 1);
									letterBank.remove(h);

									for (int f = 0; f < 3; f++) {

										if (letterBank.size() < 3) {

											for (int z = answer.size() - 1; letterBank.size() < 3; z--) {

												letterBank.add(answer.get(z));
												currentAppearance.put(answer.get(z),
														currentAppearance.get(answer.get(z)) - 1);
												answer.remove(z);

											}
										}
										sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

										answer.add(letterBank.get(f));
										currentAppearance.put(letterBank.get(f),
												currentAppearance.get(letterBank.get(f)) + 1);
										letterBank.remove(f);

										for (int o = 0; o < 2; o++) {

											if (letterBank.size() < 2) {

												for (int z = answer.size() - 1; letterBank.size() < 2; z--) {

													letterBank.add(answer.get(z));
													currentAppearance.put(answer.get(z),
															currentAppearance.get(answer.get(z)) - 1);
													answer.remove(z);

												}
											}
											sortLetterBank(input.toLowerCase(), letterBank, currentAppearance);

											answer.add(letterBank.get(o));
											currentAppearance.put(letterBank.get(o),
													currentAppearance.get(letterBank.get(o)) + 1);
											letterBank.remove(o);

											for (int k = 0; k < 1; k++) {

												sortLetterBank(input, letterBank, currentAppearance);

												// Add the last letter
												answer.add(letterBank.get(k));
												letterBank.remove(k);

												// Make the string and add it to the answers
												StringBuilder builder = new StringBuilder();
												for (Character c : answer) {
													builder.append(c);
												}
												options.add(builder.toString());

												// Remove the letter you just added, so that there are only 2 letters,
												// and add it to the letterbank
												letterBank.add(answer.get(answer.size() - 1));
												answer.remove(answer.size() - 1);

											}
										}
									}
								}
							}

						}
					}
				}

			}
		}
		return options;
	}

	public static int ordinalIndexOf(String str, String substr, int n) {
		int pos = str.indexOf(substr); // pos = 0
		while (--n > 0 && pos != -1) // while your n value is greater than 1 and your letter actually exists in the
										// string
			pos = str.indexOf(substr, pos + 1); // Resets pos to the next index of your letter, loops n-1 times
		return pos;
	}

	public static void sortLetterBank(String input, ArrayList<Character> letterBank,
			Map<Character, Integer> currentAppearance) {

		Map<Character, Integer> change = new HashMap<>();
		for (Character letter : letterBank) {
			change.put(letter, 0);
		}

		for (int i = 0; i < letterBank.size() - 1; i++) {

			for (int j = i + 1; j < letterBank.size(); j++) {
				// input.indexOf('t') = 2

				if (ordinalIndexOf(input, letterBank.get(j).toString(),
						(Integer) currentAppearance.get(letterBank.get(j))) < ordinalIndexOf(input,
								letterBank.get(i).toString(), (Integer) currentAppearance.get(letterBank.get(i)))) {

					char temp = letterBank.get(i);
					letterBank.set(i, letterBank.get(j));
					letterBank.set(j, temp);

				}
			}
			currentAppearance.put(letterBank.get(i), currentAppearance.get(letterBank.get(i)) + 1);
			change.put(letterBank.get(i), change.get(letterBank.get(i)) + 1);
		}
		for (Map.Entry<Character, Integer> entry : currentAppearance.entrySet()) {
			if (change.containsKey(entry.getKey())) {
				currentAppearance.put(entry.getKey(), entry.getValue() - change.get(entry.getKey()));
			}
		}
	}
}
