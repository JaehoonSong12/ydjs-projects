using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.AI;

public enum AIState
{
    Patrol,
    TrackMovingWaypoint
}

[RequireComponent(typeof(NavMeshAgent))]
public class MinionAI : MonoBehaviour
{
    public NavMeshAgent navMeshAgent;
    public Animator animator;
    public GameObject[] waypoints;
    private int currWaypoint = -1;

    // [#2-1] Moving Waypoint
    public AIState aiState;
    public GameObject movingWaypoint;
    public GameObject destinationTracker;
    Vector3 PredictPosition(VelocityReporter reporter)
    {
        float lookaheadTime = Mathf.Clamp(Vector3.Distance(transform.position, reporter.transform.position) / navMeshAgent.speed, 0, 2);
        return reporter.transform.position + reporter.velocity * lookaheadTime;
    }
    void TrackMovingWaypoint()
    {
        // [#2-2] Position Prediction
        Vector3 predictedPosition = PredictPosition(movingWaypoint.GetComponent<VelocityReporter>());
        if (NavMesh.Raycast(transform.position, predictedPosition, out NavMeshHit hit, NavMesh.AllAreas))
        {
            predictedPosition = hit.position;
        }
        destinationTracker.transform.position = predictedPosition;
        navMeshAgent.SetDestination(predictedPosition);

        if (!navMeshAgent.pathPending && navMeshAgent.remainingDistance < 0.5f)
        {
            // Switch back to patrol
            aiState = AIState.Patrol;
        }
    }
    void Start()
    {
        navMeshAgent = GetComponent<NavMeshAgent>();
        animator = GetComponent<Animator>();
        aiState = AIState.Patrol;
        SetNextWaypoint();
    }


    // Update is called once per frame
    void Update()
    {
        // [#3] state machine
        switch (aiState)
        {
            case AIState.Patrol:
                Patrol();
                if (currWaypoint == waypoints.Length - 1) aiState = AIState.TrackMovingWaypoint;
                break;
            case AIState.TrackMovingWaypoint:
                TrackMovingWaypoint();
                break;
        }
        // [#4] Update animator with the agent's velocity
        animator.SetFloat("vely", navMeshAgent.velocity.magnitude / navMeshAgent.speed);
    }

    void Patrol()
    {
        if (!navMeshAgent.pathPending && navMeshAgent.remainingDistance < 0.5f)
        {
            SetNextWaypoint();
        }
    }

    void SetNextWaypoint()
    {
        if (waypoints.Length == 0) return;
        currWaypoint = (currWaypoint + 1) % waypoints.Length;
        navMeshAgent.SetDestination(waypoints[currWaypoint].transform.position);
    }
}