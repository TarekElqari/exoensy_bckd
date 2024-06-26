package ma.emsi.expensebackend.service.impl;

import java.time.LocalDate;
import java.util.List;

import ma.emsi.expensebackend.entity.Budget;
import org.springframework.stereotype.Service;
import ma.emsi.expensebackend.entity.Depot;
import ma.emsi.expensebackend.repository.DepotRepository;
import ma.emsi.expensebackend.service.facade.DepotFacade;

@Service
public class DepotFacadeImpl implements DepotFacade {

    private final DepotRepository depotRepository;
    private final BudgetFacadeImpl budgetFacadeImpl;

    public DepotFacadeImpl(DepotRepository depotRepository, BudgetFacadeImpl budgetFacadeImpl) {
        this.depotRepository = depotRepository;
        this.budgetFacadeImpl = budgetFacadeImpl;
    }

    @Override
    public Depot saveDepot(Depot depot, Long userId) {
        Budget budget = budgetFacadeImpl.findBudgetByUserId(userId);
        depot.setDateDepot(LocalDate.now());
        budget.setMontant(budget.getMontant() + depot.getMontant());
        budgetFacadeImpl.ajouterBudget(budget);

        return depotRepository.save(depot);
    }


    @Override
    public void deleteDepot(Long depotId) {
        depotRepository.deleteById(depotId);
    }

    @Override
    public Depot updateDepot(Depot depot) {
        return depotRepository.save(depot);
    }

    @Override
    public List<Depot> getAllDepots() {
        return depotRepository.findAll();
    }
}