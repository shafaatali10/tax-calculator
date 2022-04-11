INSERT INTO tax_rule(id, income_start, income_end, tax_on_each_dollar, slab_addition, is_active)
	VALUES (1, 0,18200, 0, 0, true);

INSERT INTO tax_rule(id, income_start, income_end, tax_on_each_dollar, slab_addition, is_active)
	VALUES (2, 18201,37000, 0.19, 0, true);

INSERT INTO tax_rule(id, income_start, income_end, tax_on_each_dollar, slab_addition, is_active)
	VALUES (3, 37001, 87000, 0.325, 3572, true);

INSERT INTO tax_rule(id, income_start, income_end, tax_on_each_dollar, slab_addition, is_active)
	VALUES (4, 87001, 180000, 0.37, 19822, true);

INSERT INTO tax_rule(id, income_start, income_end, tax_on_each_dollar, slab_addition, is_active)
	VALUES (5, 180001, 2147483647, 0.45, 54232, true);