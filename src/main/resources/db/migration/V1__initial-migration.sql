-- Inserir armas
INSERT INTO armas (id, nome, tipo, descricao, dano, negacao_dano) VALUES
    ('e4eaaaf2-d142-11e1-b3e4-080027620cdd', 'Espada Longa', 0, 'Uma espada longa com grande alcance e poder.', 100, 10),
    ('d94a6f18-d142-11e1-b3e4-080027620cdd', 'Adaga Sombria', 1, 'Uma adaga leve e rápida, usada para ataques furtivos.', 40, 5),
    ('d94a6f19-d142-11e1-b3e4-080027620cdd', 'Cajado Arcano', 2, 'Um cajado mágico imbuído com poder arcano.', 120, 0),
    ('d94a6f20-d142-11e1-b3e4-080027620cdd', 'Talisma Sagrado', 3, 'Um talisma que canaliza energia divina.', 50, 20),
    ('d94a6f21-d142-11e1-b3e4-080027620cdd', 'Arco Curto', 4, 'Um arco leve, adequado para arqueiros ágeis.', 70, 0),
    ('d94a6f22-d142-11e1-b3e4-080027620cdd', 'Machado de Guerra', 5, 'Um machado grande usado por guerreiros de força.', 130, 10),
    ('d94a6f23-d142-11e1-b3e4-080027620cdd', 'Escudo de Aço', 6, 'Um escudo robusto que oferece grande defesa.', 20, 60);

-- Inserir armaduras
INSERT INTO armaduras (id, nome, tipo, descricao, defesa) VALUES
    ('f8a2b1f2-d142-11e1-b3e4-080027620cdd', 'Armadura de Couro', 0, 'Uma armadura leve feita de couro resistente.', 30),
    ('f8a2b1f3-d142-11e1-b3e4-080027620cdd', 'Cota de Malha', 1, 'Uma armadura média composta de pequenos anéis de metal interligados.', 60),
    ('f8a2b1f4-d142-11e1-b3e4-080027620cdd', 'Armadura de Placas', 2, 'Uma armadura pesada feita de placas de metal.', 100);

-- Inserir pets
INSERT INTO pets (id, nome, descricao, dano, defesa) VALUES
    ('c3b5f2d2-d142-11e1-b3e4-080027620cdd', 'Lobo de Batalha', 'Um lobo treinado para o combate.', 50, 20),
    ('c3b5f2d3-d142-11e1-b3e4-080027620cdd', 'Falcão Peregrino', 'Um falcão ágil e veloz.', 30, 10),
    ('c3b5f2d4-d142-11e1-b3e4-080027620cdd', 'Urso Pardo', 'Um urso poderoso com alta capacidade de defesa.', 70, 50);

-- Inserir personagens
INSERT INTO personagens (id, nome, sexo, raca, classe, arma_principal_id, arma_secundaria_id, armadura_id, pet_id) VALUES
    ('a1b2c3d4-e5f6-7890-abcd-1234567890ab', 'Aldor', 0, 0, 0, 'e4eaaaf2-d142-11e1-b3e4-080027620cdd', 'd94a6f23-d142-11e1-b3e4-080027620cdd', 'f8a2b1f4-d142-11e1-b3e4-080027620cdd', 'c3b5f2d2-d142-11e1-b3e4-080027620cdd'),
    ('a2b3c4d5-e6f7-8901-abcd-1234567890ab', 'Merlin', 0, 1, 1, 'd94a6f19-d142-11e1-b3e4-080027620cdd', NULL, 'f8a2b1f2-d142-11e1-b3e4-080027620cdd', 'c3b5f2d3-d142-11e1-b3e4-080027620cdd'),
    ('a3b4c5d6-e7f8-9012-abcd-1234567890ab', 'Legolas', 0, 1, 2, 'd94a6f21-d142-11e1-b3e4-080027620cdd', NULL, 'f8a2b1f2-d142-11e1-b3e4-080027620cdd', 'c3b5f2d3-d142-11e1-b3e4-080027620cdd'),
    ('a4b5c6d7-e8f9-0123-abcd-1234567890ab', 'Arthur', 0, 0, 3, 'e4eaaaf2-d142-11e1-b3e4-080027620cdd', 'd94a6f23-d142-11e1-b3e4-080027620cdd', 'f8a2b1f4-d142-11e1-b3e4-080027620cdd', 'c3b5f2d4-d142-11e1-b3e4-080027620cdd'),
    ('a5b6c7d8-e9f0-1234-abcd-1234567890ab', 'Shadow', 1, 3, 4, 'd94a6f18-d142-11e1-b3e4-080027620cdd', NULL, 'f8a2b1f2-d142-11e1-b3e4-080027620cdd', 'c3b5f2d2-d142-11e1-b3e4-080027620cdd'),
    ('a6b7c8d9-e0f1-2345-abcd-1234567890ab', 'Luminara', 1, 0, 3, 'd94a6f20-d142-11e1-b3e4-080027620cdd', NULL, 'f8a2b1f3-d142-11e1-b3e4-080027620cdd', 'c3b5f2d3-d142-11e1-b3e4-080027620cdd');
