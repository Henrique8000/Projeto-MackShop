# DIÁRIO DE BORDO

## 1. Parte mais desafiadora do projeto e como foi superada:
A parte mais desafiadora foi a gestão dos vetores paralelos e da matriz para o histórico de vendas, especialmente na função `buscarVendaEspecifica` para reconstruir a nota fiscal de um pedido antigo. A superação envolveu um planejamento cuidadoso de como os dados seriam armazenados e recuperados, garantindo que o ID do pedido, os IDs dos produtos e as quantidades vendidas fossem corretamente associados e que o valor total fosse recalculado ou recuperado de forma consistente.

## 2. Uso de IA como ferramenta:
Sim, utilizei uma IA (Gemini) para auxiliar no desenvolvimento deste projeto. O prompt inicial foi: 

> "A partir da base de código fornecida, corrija erros e os destaque com comentários de como foram arrumados, além disso, sugira uma forma de criação para a estrutura a seguir: 
> - Ver histórico de vendas: Percorre os vetores de histórico e imprime um resumo de todas as vendas finalizadas (ex: Pedido ID: 1001 - Valor Total: R$ 1070.50). 
> - Buscar venda específica do histórico: Solicita um ID de Pedido ao usuário. O sistema deve então percorrer a matriz `historicoItensVendidos`, encontrar todos os itens correspondentes àquele pedido e reimprimir a nota fiscal completa daquela transação."

Além disso, durante o desenvolvimento do código, foi utilizado o Copilot+Vscode para resolver pequenos erros e bugs na criação.

### Contribuições da IA:
A IA foi fundamental para:
- Correção de bugs e pequenos erros de lógica e sintaxe. 
- Sugerir a estrutura para o histórico de vendas e a recuperação de dados para a busca de vendas específicas.

O resultado fornecido pela IA serviu como uma base sólida e funcional, permitindo que eu me concentrasse em refinar a lógica, garantir a conformidade com os requisitos específicos do projeto (como o uso exclusivo de vetores e matrizes) e realizar os testes necessários para validar todas as funcionalidades. A capacidade de gerar um esqueleto funcional rapidamente acelerou significativamente o processo de desenvolvimento.
